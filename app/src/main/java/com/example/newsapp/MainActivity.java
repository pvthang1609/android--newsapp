package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newsapp.Models.NewsHeadLines;
import com.example.newsapp.Models.OnFetchDataListener;
import com.example.newsapp.Models.RequestManager;
import com.example.newsapp.Models.SelectListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    CustomAdapter adapter;
    RequestManager manager;
    Button buttonGeneral, buttonTechnology, buttonSports, buttonScience, buttonHealth,
            buttonBusiness, buttonEntertainment;
    SearchView searchView;
    String category = "general";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_main);
        searchView = findViewById(R.id.search_view);

        buttonGeneral = findViewById(R.id.button_general);
        buttonTechnology = findViewById(R.id.button_technology);
        buttonSports = findViewById(R.id.button_sports);
        buttonScience = findViewById(R.id.button_science);
        buttonHealth = findViewById(R.id.button_health);
        buttonBusiness = findViewById(R.id.button_business);
        buttonEntertainment = findViewById(R.id.button_entertainment);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetch data from server newsapi.org...!");
        progressDialog.show();

        manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "", category);

        buttonGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabledButton();
                category = "general";
                buttonGeneral.setEnabled(false);
                progressDialog.setTitle("Fetch news with category general from server newsapi.org...!");
                progressDialog.show();
                manager.getNewsHeadlines(listener, "", category);
            }
        });

        buttonTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabledButton();
                category = "technology";
                buttonTechnology.setEnabled(false);
                progressDialog.setTitle("Fetch news with category technology from server newsapi.org...!");
                progressDialog.show();
                manager.getNewsHeadlines(listener, "", category);
            }
        });

        buttonSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabledButton();
                category = "sports";
                buttonSports.setEnabled(false);
                progressDialog.setTitle("Fetch news with category sports from server newsapi.org...!");
                progressDialog.show();
                manager.getNewsHeadlines(listener, "", category);
            }
        });

        buttonScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabledButton();
                category = "science";
                buttonScience.setEnabled(false);
                progressDialog.setTitle("Fetch news with category science from server newsapi.org...!");
                progressDialog.show();
                manager.getNewsHeadlines(listener, "", category);
            }
        });

        buttonHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabledButton();
                category = "health";
                buttonHealth.setEnabled(false);
                progressDialog.setTitle("Fetch news with category health from server newsapi.org...!");
                progressDialog.show();
                manager.getNewsHeadlines(listener, "", category);
            }
        });

        buttonEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabledButton();
                category = "entertainment";
                buttonEntertainment.setEnabled(false);
                progressDialog.setTitle("Fetch news with category entertainment from server newsapi.org...!");
                progressDialog.show();
                manager.getNewsHeadlines(listener, "", category);
            }
        });

        buttonBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabledButton();
                category = "business";
                buttonBusiness.setEnabled(false);
                progressDialog.setTitle("Fetch news with category business from server newsapi.org...!");
                progressDialog.show();
                manager.getNewsHeadlines(listener, "", category);
            }
        });

        searchView.setOnQueryTextListener(searchListener);
    }

    private final SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            manager.getNewsHeadlines(listener, query, category);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(List<NewsHeadLines> list, String message) {
            showNewsHeadlines(list);
            progressDialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };
    private void showNewsHeadlines(List<NewsHeadLines> list) {
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadLines headline) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class).putExtra("data", headline);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void setEnabledButton() {
        Button buttonHandle = null;
        switch (category) {
            case "general":
                buttonHandle = buttonGeneral;
                break;
            case "technology":
                buttonHandle = buttonTechnology;
                break;
            case "sports":
                buttonHandle = buttonSports;
                break;
            case "science":
                buttonHandle = buttonScience;
                break;
            case "health":
                buttonHandle = buttonHealth;
                break;
            case "business":
                buttonHandle = buttonBusiness;
                break;
            case "entertainment":
                buttonHandle = buttonEntertainment;
                break;
        }
        if (buttonHandle != null) {
            buttonHandle.setEnabled(true);
        }
    }
}