package com.example.anusha.github.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.anusha.github.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.txt_content)
    public TextView txt_content;

    @BindView(R.id.txt_heading)
    public TextView txt_heading;

    public GithubViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this , itemView);
    }
}
