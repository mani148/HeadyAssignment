package com.assignment.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import com.assignment.BR;
import com.assignment.R;
import com.assignment.databinding.ItemRowBinding;
import com.assignment.fragment.CategoriesFragment;
import com.assignment.listerner.CustomClickListener;
import com.assignment.model.Category;
import com.assignment.room_db.entity.Categories;
import com.assignment.room_db.entity.Product;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements CustomClickListener {

    private List<Categories> dataModelList;
    private Context context;
    private CategoriesFragment fragment;

    public MyRecyclerViewAdapter(List<com.assignment.room_db.entity.Categories>  dataModelList, Context ctx, CategoriesFragment fragment) {
        this.dataModelList = dataModelList;
        context = ctx;
        this.fragment=fragment;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        ItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_row, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Categories dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setItemClickListener(this);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }



    @Override
    public void cardClicked(Categories f) {

        fragment.moveToSubCategories(f.getId());

    }

    @Override
    public void prodClicked(Product f) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemRowBinding itemRowBinding;

        public ViewHolder(ItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(com.assignment.BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }


}

