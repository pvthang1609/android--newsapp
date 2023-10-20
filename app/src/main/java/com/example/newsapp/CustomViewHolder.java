package com.example.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView textTitle, textSource, textDescription, textPublishAt;
    ImageView imgHeadline;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        textTitle = itemView.findViewById(R.id.text_title);
        textDescription = itemView.findViewById(R.id.text_description);
        textSource = itemView.findViewById(R.id.text_source);
        textPublishAt = itemView.findViewById(R.id.text_publish_at);
        imgHeadline = itemView.findViewById(R.id.img_headline);
        cardView = itemView.findViewById(R.id.main_container);
    }
}
