package com.example.alextarasyuk.news_api_keepsolid.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by AlexTarasyuk
 */

public class Util {
    public static boolean isConnection(final Context context) {
        ConnectivityManager cvm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cvm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }

        return false;
    }

    public static boolean isLandscape(final Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        }
        return false;
    }
}
