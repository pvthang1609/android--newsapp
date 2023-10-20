package com.example.newsapp.Models;

import java.util.List;

public interface OnFetchDataListener {
    void onFetchData(List<NewsHeadLines> list, String message);
    void onError(String message);
}
