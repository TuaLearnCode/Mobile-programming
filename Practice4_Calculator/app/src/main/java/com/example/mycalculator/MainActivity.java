package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mycalculator.databinding.ActivityMainBinding;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ScreenModel screenModel;
    private HistoryModel historyModel;
    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
    private boolean dot = false; // cờ kiểm tra dấu thập phân

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Khởi tạo ViewModel
        historyModel = new ViewModelProvider(this).get(HistoryModel.class);
        screenModel = new ViewModelProvider(this).get(ScreenModel.class);

        // Quan sát dữ liệu hiển thị chính
        screenModel.getString().observe(this, string -> {
            binding.tvScreen.setText(string);

        });

        // Quan sát dữ liệu lịch sử
        historyModel.getHistory().observe(this, strings -> {
            String temp = "";
            int n = strings.size();
            if (n == 1) {
                temp = strings.get(0);
            } else if (n > 1) {
                temp = strings.get(n - 2) + "\n" + strings.get(n - 1);
            }
            binding.tvHistory.setText(temp);
        });
    }

    // Xử lý khi nhấn nút
    public void ButtonHandler(View v) {
        String btText = ((Button) v).getText().toString(); // Lấy nội dung nút
        String str = screenModel.getString().getValue();   // Lấy chuỗi hiện tại
        char last = '0';
        if (!"".equals(str)) {
            last = str.charAt(str.length() - 1);
        }

        switch (btText) {
            // ====== Các số ======
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                screenModel.addString(btText);
                break;

            // ====== Các toán tử ======
            case "+": case "-": case "*": case "/":
                dot = false;
                if (last == '+' || last == '-' || last == '*' || last == '/') {
                    screenModel.removeLast(); // Xóa toán tử cũ nếu có
                }
                screenModel.addString(btText);
                break;

            // ====== Xóa 1 ký tự ======
            case "DEL":
                if (last == '.') dot = false;
                screenModel.removeLast();
                break;

            // ====== Xóa toàn bộ ======
            case "C":
                screenModel.reset();
                historyModel.clear();
                break;

            // ====== Dấu thập phân ======
            case ".":
                if (!dot) {
                    dot = true;
                    screenModel.addString(btText);
                } else {
                    calculatorWarning("Đã tồn tại dấu thập phân");
                }
                break;

            // ====== Thực hiện phép tính ======
            case "=":
                if (last == '+' || last == '-' || last == '*' || last == '/') {
                    calculatorWarning("Lỗi: Dư toán tử " + last + " ở cuối");
                } else if (str.length() > 0) {
                    dot = false;
                    solve();
                    screenModel.reset();
                } else {
                    historyModel.addHistory("0");
                    screenModel.reset();
                }
                break;
        }
    }

    // Thông báo lỗi nhỏ (Toast)
    private void calculatorWarning(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    // Thực hiện tính toán
    private void solve() {
        String str = screenModel.getString().getValue();
        try {
            // Thực thi biểu thức toán học bằng JavaScript engine
            Object result = engine.eval(str);
            historyModel.addHistory(str + " = " + result);
        } catch (ScriptException e) {
            calculatorWarning("Can't calculate");
        }
    }
}
