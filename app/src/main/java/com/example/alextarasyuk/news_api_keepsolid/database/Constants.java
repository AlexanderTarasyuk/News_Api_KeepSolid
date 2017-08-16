package com.example.alextarasyuk.news_api_keepsolid.database;

/**
 * Created by AlexTarasyuk
 */

public class Constants {

    public final static int LOADER_ID = 1;

    public final static String URL_KEY = "URL_KEY";

    public final static String DB_NAME = "news_db";
    public final static String DB_TABLE_NAME = "news";
    public final static String COLUMN_ID_PRIMARY = "_id";
    public final static String COLUMN_ID = "newsId";
    public final static String COLUMN_NAME = "newsName";
    public final static String COLUMN_NEWS_URL = "newsURL";
    public final static String COLUMN_URL_TO_IMAGE = "imageURL";
    public final static int DB_VERSION = 1;

    public static final String DB_CREATE =
            "create table "
                    + DB_TABLE_NAME
                    + "(" + COLUMN_ID_PRIMARY + " integer primary key autoincrement, "
                    + COLUMN_ID + " integer, " + COLUMN_NAME
                    + " text, " + COLUMN_NEWS_URL + " text, " + COLUMN_URL_TO_IMAGE + " text," +
            " UNIQUE ( " + COLUMN_ID + " ) ON CONFLICT IGNORE" + ");";

    public static final String DB_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DB_TABLE_NAME;
}
