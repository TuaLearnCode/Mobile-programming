package com.example.huynhtruongthaoduyen;
import android.content.Context;
import com.example.huynhtruongthaoduyen.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        }

        Movie movie = movies.get(position);

        ImageView imgPoster = convertView.findViewById(R.id.imgPoster);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtTime = convertView.findViewById(R.id.txtTime);
        TextView txtType = convertView.findViewById(R.id.txtType);

        imgPoster.setImageResource(movie.getPoster());
        txtName.setText(movie.getName());
        txtTime.setText(movie.getTime());
        txtType.setText("Loại: " + movie.getType());

        return convertView;
    }
}
