package com.example.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NewsViewHolder> {

    List<Article> articleList;
    RecyclerAdapter(List<Article> articleList){

        this.articleList = articleList;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_view,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.titleView.setText(article.getTitle());
        holder.sourceView.setText(article.getSource().getName());

        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.baseline_error_24)
                .placeholder(R.drawable.baseline_error_24)
                .into(holder.imageView);


        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(),AcitivityShow.class);
            i.putExtra("url",article.getUrl());
            v.getContext().startActivity(i);
        });


    }

    void updateData(List<Article>data){
        articleList.clear();
        articleList.addAll(data);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

         TextView titleView, sourceView;
         ImageView imageView;
         public NewsViewHolder(@NonNull View itemView) {
             super(itemView);
             titleView = itemView.findViewById(R.id.article_title);
             sourceView = itemView.findViewById(R.id.sourceId);
             imageView = itemView.findViewById(R.id.news_recyclcer_Id);
         }
     }
}
