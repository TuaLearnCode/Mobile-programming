package com.example.huynhtruongthaoduyen;
import com.example.huynhtruongthaoduyen.Movie;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView imgPoster = findViewById(R.id.imgPosterDetail);
        TextView txtName = findViewById(R.id.txtNameDetail);
        TextView txtType = findViewById(R.id.txtTypeDetail);
        TextView txtDuration = findViewById(R.id.txtDurationDetail);
        TextView txtTimeRoom = findViewById(R.id.txtTimeRoomDetail);
        TextView txtPrice = findViewById(R.id.txtPriceDetail);
        TextView txtDescription = findViewById(R.id.txtDescriptionDetail);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnBook = findViewById(R.id.btnBook);


        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        int poster = intent.getIntExtra("poster", 0);
        String name = intent.getStringExtra("name");
        String type = intent.getStringExtra("type");
        String duration = intent.getStringExtra("duration");
        String time = intent.getStringExtra("time");
        String room = intent.getStringExtra("room");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("description");

        // Set dữ liệu vào view
        imgPoster.setImageResource(poster);
        txtName.setText(name);
        txtType.setText("Thể loại: " + type);
        txtDuration.setText("Thời lượng: " + duration);
        txtTimeRoom.setText("Suất: " + time + " - Phòng " + room);
        txtPrice.setText("Giá vé: " + price);
        txtDescription.setText(description);

        // Nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Nút đặt vé (tạm thời chỉ hiện Toast)
        btnBook.setOnClickListener(v -> {
            String[] suatChieu = {"18:00", "19:00", "20:30", "21:00"};

            new AlertDialog.Builder(MovieDetailActivity.this)
                    .setTitle("Chọn suất chiếu")
                    .setItems(suatChieu, (dialog, which) -> {
                        String gioChieu = suatChieu[which];

                        // Sau khi chọn giờ → cho chọn ghế
                        String[] gheNgoi = {"A1", "A2", "A3", "B1", "B2", "B3"};
                        new AlertDialog.Builder(MovieDetailActivity.this)
                                .setTitle("Chọn ghế ngồi")
                                .setItems(gheNgoi, (d, w) -> {
                                    String ghe = gheNgoi[w];

                                    // Lưu vào DB
                                    DatabaseHelper db = new DatabaseHelper(MovieDetailActivity.this);
                                    db.insertTicket(name, gioChieu, ghe, "Đang giữ chỗ");

                                    Toast.makeText(MovieDetailActivity.this,
                                            "Đặt vé thành công!\nSuất: " + gioChieu + " | Ghế: " + ghe,
                                            Toast.LENGTH_LONG).show();
                                })
                                .show();
                    })
                    .show();
        });



    }
}
