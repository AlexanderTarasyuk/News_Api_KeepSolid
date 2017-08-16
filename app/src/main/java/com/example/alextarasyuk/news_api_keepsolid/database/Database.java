package com.example.alextarasyuk.news_api_keepsolid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.alextarasyuk.news_api_keepsolid.model.Article;

import java.util.List;

/**
 * Created by AlexTarasyuk
 */

public class Database {

    private final Context mContext;
    private SQLiteDatabase mDataBase;
    private DBHelper mDBHelper;

    public Database(Context context) {
        mContext = context;
    }

    public void open() {
        mDBHelper = new DBHelper(mContext, Constants.DB_NAME, null, Constants.DB_VERSION);
        mDataBase = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper != null) {
            mDBHelper.close();
        }
    }

    public Cursor getAllData() {
        return mDataBase.query(Constants.DB_TABLE_NAME, null, null,
                null, null, null, Constants.COLUMN_ID_PRIMARY + " DESC");
    }

    public void clearData() {
        mDataBase.delete(Constants.DB_TABLE_NAME, null, null);
    }

    private void addNews(Article news) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.COLUMN_NAME, news.getTitle());
        cv.put(Constants.COLUMN_NEWS_URL, news.getUrl());
        cv.put(Constants.COLUMN_URL_TO_IMAGE, news.getUrlToImage());
        mDataBase.insert(Constants.DB_TABLE_NAME, null, cv);
    }

    public void addApiData(List<Article> news) {
        if (news.size() != 0) {
            for (int i = news.size() - 1; i >= 0; i--) {
                addNews(news.get(i));
            }
        }
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Constants.DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(Constants.DB_DELETE_ENTRIES);
            onCreate(sqLiteDatabase);
        }
    }
}
