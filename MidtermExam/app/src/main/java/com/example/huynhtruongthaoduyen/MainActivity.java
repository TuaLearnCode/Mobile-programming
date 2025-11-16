package com.example.huynhtruongthaoduyen;
import com.example.huynhtruongthaoduyen.Movie;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvMovies;
    private ArrayList<Movie> movieList;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvMovies = findViewById(R.id.lv_DanhSachPhim);
        Button btnLichSu = findViewById(R.id.btnLichSu);
        movieList = new ArrayList<>();

        // Thêm dữ liệu mẫu
        movieList.add(new Movie(R.drawable.poster1, "Avengers: Endgame", "19:00", "3D", "180 phút", "3", "70,000", "Tran chien cuoi cùng..."));
        movieList.add(new Movie(R.drawable.poster2, "Frozen II", "21:00", "2D", "180 phút", "2", "70,000", "Phim hanh dong"));
        movieList.add(new Movie(R.drawable.poster3, "June", "20:30", "IMAX", "120 phút", "1", "70,000", "Kich tich, hap dan"));

        movieAdapter = new MovieAdapter(this, movieList);
        lvMovies.setAdapter(movieAdapter);

        lvMovies.setOnItemClickListener((parent, view, position, id) -> {
            Movie movie = movieList.get(position);
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra("poster", movie.getPoster());
            intent.putExtra("name", movie.getName());
            intent.putExtra("type", movie.getType());
            intent.putExtra("duration", movie.getDuration());
            intent.putExtra("time", movie.getTime());
            intent.putExtra("room", movie.getRoom());
            intent.putExtra("price", movie.getPrice());
            intent.putExtra("description", movie.getDescription());
            startActivity(intent);
        });

        btnLichSu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });



    }
}
