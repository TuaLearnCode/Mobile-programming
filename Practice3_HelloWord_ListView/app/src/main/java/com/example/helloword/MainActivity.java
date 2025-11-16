package com.example.helloword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.helloword.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

//    private TextView tvCount;
//    private FloatingActionButton btnAdd;
//    private FloatingActionButton btnSub;
//
//    private FloatingActionButton btnReset;

    private MyViewModel model;
    private ActivityMainBinding binding;
    private ListView lvCount;
    private ArrayList<Integer> arrayList;
    private ArrayAdapter<Integer> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
     //   setContentView(R.layout.activity_main);

        //sử dụng binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Xử lý xoay màn hình không bị mất dữ liệu
        model = new ViewModelProvider(this).get(MyViewModel.class);

        //Sử dụng listView
        arrayList = new ArrayList<Integer>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList );
        binding.lvCount.setAdapter(arrayAdapter);
//        tvCount = findViewById(R.id.tvCount);
//        btnAdd = findViewById(R.id.btnAdd);
//        btnSub = findViewById(R.id.btnSub);
//        btnReset = findViewById(R.id.btnReset);

        // hàm dùng cho xoay màn hình
        model.getNumbers().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText("" + integer);
                arrayList.add(integer);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.increaseNumber();

            }
        });

        // nhấn giữ nào một item thì item đó bị mất đi
        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
       @Override
       public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
           arrayList.remove(position);
           arrayAdapter.notifyDataSetChanged();
           return true; // true để báo là đã xử lý xongg
       }
   });


        // hiện ra detail_Item của listview
        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("number", arrayList.get(position).toString());
                startActivity(intent);
            }
        });

        // Nút sub
        binding.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.decreaseNumber();
            }
        });

        //Nút reset
        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.resetNumber();
            }
        });

    }
}
