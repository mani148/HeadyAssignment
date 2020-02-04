package com.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.assignment.R;
import com.assignment.adapter.MyRecyclerViewAdapterSub;
import com.assignment.databinding.FragmentSubcategoriesBinding;
import com.assignment.network.APIInterface;
import com.assignment.room_db.AppDatabase;

import java.util.List;


public class SubCategoriesFragment extends Fragment {

    FragmentSubcategoriesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_categories, container, false);

         binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_subcategories, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        Bundle args = getArguments();

        int cateId=args.getInt("cateId");
        getProduct(cateId);
        return view;
    }

    public void getProduct(int cate_id){

        List<com.assignment.room_db.entity.Product> products= AppDatabase.getInstance(getActivity()).productDao().getAllProdcut(cate_id);
        if(products!=null && products.size()>0){
        MyRecyclerViewAdapterSub myRecyclerViewAdapter = new MyRecyclerViewAdapterSub(products, getActivity(),this);
        binding.setMyAdapter(myRecyclerViewAdapter);
        }else{
            Toast.makeText(getContext(),"SubCategories are not found",Toast.LENGTH_SHORT).show();
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove(this);
            trans.commit();
            manager.popBackStack();}
    }

    public void moveToDetail(int prodId){
        Bundle args = new Bundle();
        args.putInt("prodId",prodId);
        FragmentManager fm = getActivity().getSupportFragmentManager();
     //   Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
       /* if (fragment == null)*/ {
            Fragment  fragment = new CategoriesDetailFragment();
            fragment.setArguments(args);
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack("details")
                    .commit();
        }
    }


}