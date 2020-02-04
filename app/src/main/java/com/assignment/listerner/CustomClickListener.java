package com.assignment.listerner;

import com.assignment.model.Category;
import com.assignment.room_db.entity.Categories;
import com.assignment.room_db.entity.Product;

public interface CustomClickListener {

    void cardClicked(Categories f);

    void prodClicked(Product f);
}
