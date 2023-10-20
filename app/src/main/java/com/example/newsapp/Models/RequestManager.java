package com.example.newsapp.Models;

import android.content.Context;
import android.widget.Toast;

import com.example.newsapp.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("apiKey") String apiKey
        );
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlinesWithSearch(
                @Query("country") String country,
                @Query("category") String category,
                @Query("apiKey") String apiKey,
                @Query("q") String query
        );
    }

    public void getNewsHeadlines(OnFetchDataListener listener, String query, String category) {
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call;

        if (Objects.equals(query, "")) {
            call = callNewsApi.callHeadlines("us", category, context.getString(R.string.api_key));
        } else {
            call = callNewsApi.callHeadlinesWithSearch("us", category, context.getString(R.string.api_key), query);
        }
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error.", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("Fail when get data.");
                }
            });
        } catch (Exception e) {
            listener.onError("Fail when get data.");
        }
    }
}
