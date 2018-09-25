package com.example.anusha.github.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anusha.github.R;
import com.example.anusha.github.activity.SecondActivity;
import com.example.anusha.github.model.SearchResult;
import com.example.anusha.github.viewholders.GithubViewHolder;

import java.util.List;

import retrofit2.Response;

public class GithubAdapter extends RecyclerView.Adapter<GithubViewHolder> {

    private Context mContext;
    private Response<SearchResult> githublist ;

    public GithubAdapter( Context mContext ,Response<SearchResult> githublist ) {
        this.mContext = mContext;
        this.githublist = githublist ;
    }

    @NonNull
    @Override
    public GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GithubViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_github, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GithubViewHolder holder, final int position) {

        holder.txt_heading.setText(githublist.body().getRepositories().get(position).getFullName());
        holder.txt_content.setText(githublist.body().getRepositories().get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = githublist.body().getRepositories().get(position).getHtmlUrl();
                Intent i = new Intent(mContext , SecondActivity.class);
                i.putExtra("webUrl" ,url);
                mContext.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return githublist.body().getRepositories().size();
    }




}
