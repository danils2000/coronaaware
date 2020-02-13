package com.example.coronaaware;

import com.example.coronaaware.info.Article;

import java.util.ArrayList;
import java.util.List;

public class News {
    private static final News ourInstance = new News();

    private List<Article> newsList;

    public static News getInstance() {
        return ourInstance;
    }

    private News() {
        newsList =  new ArrayList<>();
    }

    public List<Article> getNewsList() {
        return newsList;
    }
}