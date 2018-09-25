package com.example.anusha.github.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.anusha.github.GithubPresenter;
import com.example.anusha.github.GithubView;
import com.example.anusha.github.R;
import com.example.anusha.github.adapter.GithubAdapter;
import com.example.anusha.github.model.SearchResult;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GithubView {

    private RecyclerView recycler_list ;
    GithubPresenter presenter ;
    private RelativeLayout parentLayout ;
    private SwipeRefreshLayout swipeRefresh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter= new GithubPresenter( MainActivity.this);
        recycler_list = findViewById(R.id.items_recyclerview);
        parentLayout = findViewById(R.id.parentLayout);
        swipeRefresh = findViewById(R.id.swiperefresh);
        showGithubList();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        showGithubList();
                        swipeRefresh.setRefreshing(false);
                    }

                }, 3000);

            }
        });

    }

    public void showGithubList()
    {
        if(isNetworkConnected())
            presenter.GithubResult();
        else {
            Snackbar  snackbar = Snackbar
                    .make(parentLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            showGithubList();
                        }
                    });
            snackbar.show();
        }
    }

    @Override
    public void getGibhubList(Response<SearchResult> response) {

        GithubAdapter adapter = new GithubAdapter(  MainActivity.this ,response);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());
        recycler_list.setAdapter(adapter);
    }

    @Override
    public void failureAlert() {

        Snackbar  snackbar = Snackbar
                .make(parentLayout, "Server Error!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        showGithubList();
                    }
                });
        snackbar.show();

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }
}
