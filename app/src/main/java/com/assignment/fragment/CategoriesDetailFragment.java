package com.assignment.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.assignment.R;
import com.assignment.databinding.FragmentCategorydetailBinding;
import com.assignment.network.APIInterface;
import com.assignment.room_db.AppDatabase;
import com.assignment.room_db.entity.Product;
import com.assignment.room_db.entity.Variants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CategoriesDetailFragment extends Fragment {

    APIInterface apiInterface;
    FragmentCategorydetailBinding binding;
    private LinearLayout lLaySize,LlayColor;
    private TextView tvPrice,tvSize,tvColor,tvName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_categorydetail, container, false);
        View view = binding.getRoot();
        Bundle args = getArguments();
        init(view);
        int prodId=args.getInt("prodId");
        getProduct(prodId);
        return view;
    }

    private void init(View view){


        lLaySize = (LinearLayout)  view.findViewById(R.id.lLaySize);
        LlayColor = (LinearLayout)  view.findViewById(R.id.LlayColor);
        tvPrice = (TextView)  view.findViewById(R.id.tvPrice);
        tvSize = (TextView)  view.findViewById(R.id.tvSize);
        tvColor = (TextView)  view.findViewById(R.id.tvColor);
        tvName = (TextView)  view.findViewById(R.id.tvName);

    }

    public void getProduct(int prodId){

       Product prod= AppDatabase.getInstance(getActivity()).productDao().getProduct(prodId);
        if(prod==null){
            tvName.setText("Product details are not available");
            return;}
        tvSize.setText("Select Size");
        tvSize.setVisibility(View.VISIBLE);
        tvColor.setText("Select Color");
        List<Variants> variants= AppDatabase.getInstance(getActivity()).variantsDao().getVariantsById(prod.getId());
        Set<Integer> sizes = new HashSet<Integer>();
        for(Variants var: variants){
            sizes.add(var.getSize());
        }
       Log.e("prod",prod.getDate_added());
       int i=0;
        if(sizes.size()>=0){
       for(Integer size: sizes){

           if(size!=null){
               i++;
               TextView tv = new TextView(getActivity());
               tv.setId(i);
               tv.setBackground(getActivity().getDrawable(R.drawable.circular_textview_drawable));
               LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
               params.setMargins(0,0,20,0);
               tv.setLayoutParams(params);
               tv.setText(""+size);
               tv.setTextColor(Color.BLACK);
               // tv.setHeight(25);
               // tv.setWidth(25);
               tv.setGravity(Gravity.CENTER);
               tv.setTag(i-1);

               lLaySize.addView(tv);

               if((int)tv.getTag() ==0){
                   lLaySize.getChildAt(0).setBackground(getActivity().getDrawable(R.drawable.circular_textview_drawable_selected));
                   ( (TextView)lLaySize.getChildAt(0)).setTextColor(Color.WHITE);
                   List<Variants> variants_;
                   if(tv.getText()!=null && tv.getText().length()>0)
                       variants_= AppDatabase.getInstance(getActivity()).variantsDao().getVariantsBySize(Integer.parseInt(tv.getText().toString()),prod.getId());
                   else
                       variants_= AppDatabase.getInstance(getActivity()).variantsDao().getVariantsBySize(0,prod.getId());

                   setColors(variants_);
                   setPrice(prod.getId(),variants.get(0).getId());
               }

               tv.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       int index = (int)view.getTag();

                       for(int i=0; i<lLaySize.getChildCount();i++){
                           if(i==index)    {
                               lLaySize.getChildAt(index).setBackground(getActivity().getDrawable(R.drawable.circular_textview_drawable_selected));
                               ( (TextView)lLaySize.getChildAt(index)).setTextColor(Color.WHITE);
                           }
                           else{
                               lLaySize.getChildAt(i).setBackground(getActivity().getDrawable(R.drawable.circular_textview_drawable));
                               ( (TextView)lLaySize.getChildAt(i)).setTextColor(Color.BLACK);
                           }}
                       lLaySize.invalidate();
                       List<Variants> variants= AppDatabase.getInstance(getActivity()).variantsDao().getVariantsBySize(Integer.parseInt(tv.getText().toString()),prod.getId());

                       setColors(variants);
                       setPrice(prod.getId(),variants.get(0).getId());

                   }
               });


           }else {
               List<Variants> variants_= AppDatabase.getInstance(getActivity()).variantsDao().getVariantsBySize(prod.getId());
               tvSize.setVisibility(View.GONE);
               setColors(variants_);
               setPrice(prod.getId(),variants_.get(0).getId());
           }

          /*
*/


       }}

       binding.setModel(prod);

    }


    public void setColors( List<Variants> variants){
        int i=0;LlayColor.removeAllViews();
        for(Variants var:variants) {

            if (var.getColor() != null) {
                i++;
                TextView tv = new TextView(getActivity());
                tv.setId(i);
                tv.setBackground(getActivity().getDrawable(R.drawable.circular_textview_drawable));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 20, 0);
                //tv.setPadding(10,10,10,10);
                tv.setLayoutParams(params);
                tv.setText(var.getColor());
                tv.setTag(i-1);
                tv.setTextColor(Color.BLACK);
                // tv.setHeight(25);
                // tv.setWidth(25);
                tv.setGravity(Gravity.CENTER);


                LlayColor.addView(tv);
                if((int)tv.getTag() ==0){
                    LlayColor.getChildAt(0).setBackground(getActivity().getDrawable(R.drawable.circular_textview_drawable_selected));
                    ( (TextView)LlayColor.getChildAt(0)).setTextColor(Color.WHITE);
                }

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int index = (int)view.getTag();

                        for(int i=0; i<LlayColor.getChildCount();i++){
                            if(i==index)    {
                                LlayColor.getChildAt(index).setBackground(getActivity().getDrawable(R.drawable.circular_textview_drawable_selected));
                                ( (TextView)LlayColor.getChildAt(index)).setTextColor(Color.WHITE);
                            }
                            else{
                                LlayColor.getChildAt(i).setBackground(getActivity().getDrawable(R.drawable.circular_textview_drawable));
                                ( (TextView)LlayColor.getChildAt(i)).setTextColor(Color.BLACK);
                            }}
                        LlayColor.invalidate();

                        setPrice(var.getProduct_id(),var.getId());
                    }
                });
            }
            LlayColor.invalidate();
            lLaySize.invalidate();
        }
    }


    public void setPrice(int prodId ,int varId){


        Variants variants= AppDatabase.getInstance(getActivity()).variantsDao().getVariantsPrice(prodId,varId);
        tvPrice.setText("Price: "+variants.getPrice());
    }

}