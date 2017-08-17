package com.example.alextarasyuk.news_api_keepsolid.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.alextarasyuk.R;
import com.example.alextarasyuk.news_api_keepsolid.database.Constants;

/**
 * Created by AlexTarasyuk
 */
//landscaping fragment
public class DetailFragment extends Fragment {

    private WebView articleDetail;
    private String url = "http://timesofindia.indiatimes.com/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        if (getArguments() != null) {
            url = getArguments().getString(Constants.URL_KEY);
        }

        articleDetail = view.findViewById(R.id.article_detail);
        articleDetail.loadUrl(url);
        articleDetail.setWebViewClient(new WebViewClient());
        articleDetail.setWebChromeClient(new WebChromeClient());

        return view;
    }
}
