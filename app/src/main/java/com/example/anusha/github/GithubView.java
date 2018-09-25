package com.example.anusha.github;

import com.example.anusha.github.model.Repository;
import com.example.anusha.github.model.SearchResult;

import retrofit2.Response;

public interface GithubView {

    void getGibhubList(Response<SearchResult> response);
    void failureAlert();
}
