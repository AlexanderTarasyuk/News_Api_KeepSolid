package com.example.alextarasyuk.news_api_keepsolid.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.alextarasyuk.R;
import com.example.alextarasyuk.news_api_keepsolid.listeners.OnItemClickListener;
import com.example.alextarasyuk.news_api_keepsolid.model.Article;
import com.example.alextarasyuk.news_api_keepsolid.database.Constants;
import com.squareup.picasso.Picasso;


/**
 * Created by AlexTarasyuk
 */

public class NewsAdapter extends DBAdapter<NewsAdapter.ViewHolder> {

    private Context context;
    private OnItemClickListener clickListener;

    public NewsAdapter(Cursor cursor, Context context) {
        super(context, cursor);
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, Cursor cursor) {
        viewHolder.tvNewsTitle.setText(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME)));
        Picasso.with(context).load(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_URL_TO_IMAGE))).into(viewHolder.ivNewsImage);
    }

    public Article getNews(int position) {
        Cursor cursor = getCursor();
        Article news = new Article();

        if (cursor.moveToPosition(position)) {
            news.setTitle(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME)));
            news.setUrl(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NEWS_URL)));
            news.setUrlToImage(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_URL_TO_IMAGE)));
        }

        return news;
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvNewsTitle;
        private ImageView ivNewsImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNewsTitle = itemView.findViewById(R.id.article_title);
            ivNewsImage = itemView.findViewById(R.id.article_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getNews(getAdapterPosition()).getUrl(), getAdapterPosition());
        }
    }
}
