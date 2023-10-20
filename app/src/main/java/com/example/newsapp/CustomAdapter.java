package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.NewsHeadLines;
import com.example.newsapp.Models.SelectListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadLines> headlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadLines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String title = headlines.get(position).getTitle();
        String description = headlines.get(position).getDescription();
        String sourceName = headlines.get(position).getSource().getName();
        String publishAt = headlines.get(position).getPublishedAt().substring(0,10) + " ";
        String urlImageHeadline = headlines.get(position).getUrlToImage();

        holder.textTitle.setText(title);
        holder.textSource.setText(sourceName);
        holder.textDescription.setText(description);
        holder.textPublishAt.setText(publishAt);
        if (urlImageHeadline != null) {
            Picasso.get().load(urlImageHeadline).into(holder.imgHeadline);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(headlines.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
