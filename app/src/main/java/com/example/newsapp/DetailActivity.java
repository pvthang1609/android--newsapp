package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Models.NewsHeadLines;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    NewsHeadLines headline;
    TextView detailTitle, detailContent;
    ImageView detailImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailTitle = findViewById(R.id.detail_title);
        detailImg = findViewById(R.id.detail_image);
        detailContent = findViewById(R.id.detail_content);
        headline = (NewsHeadLines) getIntent().getSerializableExtra("data");

        detailTitle.setText(headline.getTitle());
        detailContent.setText(headline.getContent());
        String urlToImage = headline.getUrlToImage();
        if (urlToImage != null) {
            Picasso.get().load(urlToImage).into(detailImg);
        }
    }
}