package com.assignment.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.assignment.R;
import com.assignment.adapter.MyRecyclerViewAdapter;
import com.assignment.databinding.FragmentCategoriesBinding;
import com.assignment.model.Categories;
import com.assignment.model.Category;
import com.assignment.model.Product;
import com.assignment.model.Product_;
import com.assignment.model.Ranking;
import com.assignment.model.Tax;
import com.assignment.model.Variant;
import com.assignment.network.APIClient;
import com.assignment.network.APIInterface;
import com.assignment.room_db.AppDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment {

    APIInterface apiInterface;
    FragmentCategoriesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_categories, container, false);

         binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_categories, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider


        new DisplayTask().execute();
        return view;
    }


    public void getCategories(){

        /**
         GET List Resources
         **/
         apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Categories> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {


                Log.d("TAG",response.code()+"");

                Categories resource = response.body();
              //  List<Category> categories = resource.getCategories();
                // create worker thread to insert data into database
                new InsertTask(resource).execute();
               // populateData(categories);
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.d("",t.getMessage());

                call.cancel();
            }
        });


    }

    private void populateData( List<com.assignment.room_db.entity.Categories>  categories) {

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(categories, getActivity(),this);
        binding.setMyAdapter(myRecyclerViewAdapter);

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private  class DisplayTask extends AsyncTask<Void,Void,Boolean> {


        Categories resource;
        // only retain a weak reference to the activity
        DisplayTask() {
            this.resource=resource;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {

            List<com.assignment.room_db.entity.Categories> categories = AppDatabase.getInstance(getActivity()).categoriesDao().getAlphabetizedWords();

            if(categories==null || categories.size()==0){
                if(isNetworkConnected())
                getCategories();
                else
                    Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {

            List<com.assignment.room_db.entity.Categories> categories = AppDatabase.getInstance(getActivity()).categoriesDao().getAlphabetizedWords();

            populateData(categories);
        }

    }


    private  class InsertTask extends AsyncTask<Void,Void,Boolean> {


        Categories resource;
        // only retain a weak reference to the activity
        InsertTask(Categories resource) {
            this.resource=resource;
        }

        @Override
        protected void onPreExecute() {
            showLoadingDialog();
            super.onPreExecute();
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {


            insetCategories(resource);

            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {

            dismissLoadingDialog();
            List<com.assignment.room_db.entity.Categories> categories = AppDatabase.getInstance(getActivity()).categoriesDao().getAlphabetizedWords();

            populateData(categories);
        }

    }

    @Override
    public void onResume() {
        dismissLoadingDialog();
        super.onResume();
    }

    public  void insetCategories(Categories resource){

        if(resource.getCategories()!=null && resource.getCategories().size()>0){
        AppDatabase.getInstance(getActivity()).categoriesDao().deleteAll();
        AppDatabase.getInstance(getActivity()).productDao().deleteAll();
            AppDatabase.getInstance(getActivity()).variantsDao().deleteAll();
            AppDatabase.getInstance(getActivity()).taxDao().deleteAll();

        for(Category category: resource.getCategories()){
            com.assignment.room_db.entity.Categories cate = new com.assignment.room_db.entity.Categories(category.getId(),category.getName());
        AppDatabase.getInstance(getActivity()).categoriesDao().insert(cate);

        for(Product product:category.getProducts()){
            com.assignment.room_db.entity.Product pro = new com.assignment.room_db.entity.Product(product.getId(),category.getId(),product.getName(),product.getDateAdded());
            AppDatabase.getInstance(getActivity()).productDao().insert(pro);

            for(Variant variant:product.getVariants()){
                com.assignment.room_db.entity.Variants var = new com.assignment.room_db.entity.Variants(variant.getId(),product.getId(),variant.getColor(),variant.getPrice(),variant.getSize());
                AppDatabase.getInstance(getActivity()).variantsDao().insert(var);
            }

            Tax tax_ = product.getTax();
            com.assignment.room_db.entity.Tax tax = new com.assignment.room_db.entity.Tax(product.getId(),tax_.getName(),tax_.getValue());
            AppDatabase.getInstance(getActivity()).taxDao().insert(tax);

        }

        }}

        if(resource.getRankings()!=null && resource.getRankings().size()>0){
            AppDatabase.getInstance(getActivity()).rankingsDAO().deleteAll();
            List<Ranking> ranking = resource.getRankings();

            insertRankings(ranking);

        }
    }

    public void insertRankings(List<Ranking> ranking){


        for(Ranking rank : ranking){

            try {
                for (Product_ prod : rank.getProducts()) {
                    int viewCount = 0,orderCount=0,share=0;
                    if(prod.getViewCount()!=null)
                    viewCount=prod.getViewCount();
                    if(prod.getOrderCount()!=null)
                        orderCount=prod.getOrderCount();
                    if(prod.getShares()!=null)
                     share = prod.getShares();
                    com.assignment.room_db.entity.Rankings rankings = new com.assignment.room_db.entity.Rankings(prod.getId(), viewCount, orderCount, share, rank.getRanking());
                    AppDatabase.getInstance(getActivity()).rankingsDAO().insert(rankings);
                }
            }catch (Exception e)
            {
                e.printStackTrace();

            }
        }


    }

    public void moveToSubCategories(Integer cateId){
        Bundle args = new Bundle();
        args.putInt("cateId",cateId);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        //   Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        /* if (fragment == null)*/ {
            Fragment  fragment = new SubCategoriesFragment();
            fragment.setArguments(args);
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack("subCategories")
                    .commit();
        }
    }

    private ProgressDialog progress;



    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(getActivity());
            progress.setTitle(getString(R.string.loading_title));
            progress.setMessage(getString(R.string.loading_message));
        }
        progress.show();
    }

    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }


}