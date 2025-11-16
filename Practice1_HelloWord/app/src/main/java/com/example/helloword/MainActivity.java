package com.example.helloword;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnSub;

    private FloatingActionButton btnReset;

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

        tvCount = findViewById(R.id.tvCount);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnReset = findViewById(R.id.btnReset);

        //Cách 1: Dùng thầy dạy
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt((String) tvCount.getText());
                tvCount.setText("" + (++count));
            }
        });

        // Nút sub
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt((String) tvCount.getText());
                tvCount.setText("" + (--count));
            }
        });

        //Nút reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt((String) tvCount.getText());
                tvCount.setText("0");
            }
        });

        /*
        Cách 2: Dùng Lambda expression
         btnAdd.setOnClickListener(v -> {
             int count = Integer.parseInt(tvCount.getText().toString());
             tvCount.setText("" + (++count));
         });
         */
    }
        /*
        Cách 3: Dùng hàm rồi bên xml gọi hàm qua onClick
        public void increaseCount (View v){
            int count = Integer.parseInt(tvCount.getText().toString());
            tvCount.setText("" + (++count));
        }
         */

}
