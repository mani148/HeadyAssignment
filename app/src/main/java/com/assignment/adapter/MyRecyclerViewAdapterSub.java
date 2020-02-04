package com.assignment.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.BR;
import com.assignment.R;
import com.assignment.databinding.ItemRowBinding;
import com.assignment.databinding.ItemRowSubcetegoriesBinding;
import com.assignment.fragment.CategoriesFragment;
import com.assignment.fragment.SubCategoriesFragment;
import com.assignment.listerner.CustomClickListener;
import com.assignment.room_db.entity.Categories;
import com.assignment.room_db.entity.Product;

import java.util.List;


public class MyRecyclerViewAdapterSub extends RecyclerView.Adapter<MyRecyclerViewAdapterSub.ViewHolder> implements CustomClickListener {

    private List<Product> dataModelList;
    private Context context;
    private SubCategoriesFragment fragment;

    public MyRecyclerViewAdapterSub(List<Product>  dataModelList, Context ctx, SubCategoriesFragment fragment) {
        this.dataModelList = dataModelList;
        context = ctx;
        this.fragment=fragment;
    }

    @Override
    public MyRecyclerViewAdapterSub.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        ItemRowSubcetegoriesBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_row_subcetegories, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setItemClickListener(this);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }



    @Override
    public void cardClicked(Categories f) {



    }

    @Override
    public void prodClicked(Product f) {

        fragment.moveToDetail(f.getId());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemRowSubcetegoriesBinding itemRowBinding;

        public ViewHolder(ItemRowSubcetegoriesBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(com.assignment.BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }


}

