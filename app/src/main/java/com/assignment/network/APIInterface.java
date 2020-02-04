package com.assignment.network;

import com.assignment.model.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/json")
    Call<Categories> doGetListResources();


}
