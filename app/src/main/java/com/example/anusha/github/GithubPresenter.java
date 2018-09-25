package com.example.anusha.github;

import com.example.anusha.github.NetworkLayer.Retrofit.RestClient;
import com.example.anusha.github.model.Repository;
import com.example.anusha.github.model.SearchResult;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    private GithubView view ;

    public GithubPresenter(GithubView view) {

        this.view = view ;
    }

    public  void GithubResult() {

        RestClient.getAdapter().getRepositories("query","sort" ,"order","per_page","page").enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                if(response.isSuccessful())
                {
                  view.getGibhubList(response);

                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

                view.failureAlert();

            }


        });
    }
}
