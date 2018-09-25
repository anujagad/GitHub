package com.example.anusha.github.NetworkLayer.Retrofit;

import com.example.anusha.github.model.Repository;
import com.example.anusha.github.model.Result;
import com.example.anusha.github.model.SearchResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicesInterface {

    @GET("/search/repositories")
    Call<SearchResult> getRepositories(
            @Query(value="q") String query,
            @Query("sort") String sort,
            @Query("order") String order,
            @Query("per_page") String perPage,
            @Query("page") String page
           /* Callback<Result> response*/
    );


}
