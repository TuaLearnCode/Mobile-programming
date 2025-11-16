package com.example.twonumbers;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    EditText editA, editB;
    Button btnAdd, btnSub, btnMul, btnDiv;
    ListView listViewHistory;

    ArrayList<String> History;
    ArrayAdapter<String> adapter;

    private static WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), MainActivity::onApplyWindowInsets);
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        listViewHistory = findViewById(R.id.listViewHistory);


        // Khởi tạo list + adapter
        History = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, History);
        listViewHistory.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> calculate("+"));
        btnSub.setOnClickListener(v -> calculate("-"));
        btnMul.setOnClickListener(v -> calculate("*"));
        btnDiv.setOnClickListener(v -> calculate("/"));
    }

    private void calculate(String x) {
        try {
            double a = Double.parseDouble(editA.getText().toString());
            double b = Double.parseDouble(editB.getText().toString());
            double result = 0;

            switch (x) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b != 0) result = a / b;
                    else {
                        History.add(a + " / " + b + " = Loi chia cho 0");
                        adapter.notifyDataSetChanged();
                        return;
                    }
                    break;
            }
            // Tạo chuỗi kết quả và thêm vào list
            String calc = a + " " + x + " " + b + " = " + result;
            History.add(calc);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            History.add("Vui lòng nhập số hợp lệ!");
            adapter.notifyDataSetChanged();
        }

    }
}