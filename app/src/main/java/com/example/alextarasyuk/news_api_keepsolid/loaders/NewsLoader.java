package com.example.alextarasyuk.news_api_keepsolid.loaders;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.example.alextarasyuk.news_api_keepsolid.networking.NewsApi;
import com.example.alextarasyuk.news_api_keepsolid.model.GetArticlesResponse;
import com.example.alextarasyuk.news_api_keepsolid.model.Article;
import com.example.alextarasyuk.news_api_keepsolid.database.Database;
import com.example.alextarasyuk.news_api_keepsolid.utils.Util;

import java.io.IOException;
import java.util.List;

/**
 * Created by AlexTarasyuk
 */

public class NewsLoader extends CursorLoader {

    private Database db;
    private static NewsApi.NewsService newsService;
    private GetArticlesResponse getArticlesResponse;
    private List<Article> articleList;

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Cursor loadInBackground() {

        newsService = NewsApi.getApi();
        db = new Database(getContext());
        db.open();
        Cursor cursor = null;

        try {
            if (Util.isConnection(getContext())) {
                db.clearData();
                getArticlesResponse = newsService.getArticles().execute().body();
                if (getArticlesResponse != null) {
                    articleList = getArticlesResponse.getNews();
                    db.addApiData(articleList);
                }
            }
            cursor = db.getAllData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cursor;
    }
}
