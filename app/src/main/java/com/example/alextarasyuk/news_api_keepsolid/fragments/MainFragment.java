package com.example.alextarasyuk.news_api_keepsolid.fragments;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.alextarasyuk.R;
import com.example.alextarasyuk.news_api_keepsolid.adapters.NewsAdapter;
import com.example.alextarasyuk.news_api_keepsolid.listeners.OnItemClickListener;
import com.example.alextarasyuk.news_api_keepsolid.loaders.NewsLoader;
import com.example.alextarasyuk.news_api_keepsolid.database.Constants;
import com.example.alextarasyuk.news_api_keepsolid.database.Database;
import com.example.alextarasyuk.news_api_keepsolid.utils.Util;

/**
 * Created by AlexTarasyuk
 */
//main fragment
public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener {

    private FragmentManager fm;
    private DetailFragment fragment;
    private Bundle bundle;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private Database database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container);
        database = new Database(getActivity());
        database.open();
        getActivity().getLoaderManager().initLoader(Constants.LOADER_ID, null, this);

        fm = getFragmentManager();

        bundle = new Bundle();

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    private void initAdapter() {

        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(database.getAllData(), getContext());
        }

        newsAdapter.SetOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View view, String url, int position) {
                fragment = new DetailFragment();
                bundle.putString(Constants.URL_KEY, newsAdapter.getNews(position).getUrl());
                fragment.setArguments(bundle);
                if (Util.isLandscape(getContext())) {
                    fm.beginTransaction()
                            .replace(R.id.detail_fragment, fragment)
                            .commit();
                } else {
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
            }
        });

        newsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        database.close();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new NewsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        initAdapter();
        newsAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onRefresh() {

    }
}
