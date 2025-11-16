package com.example.mycalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScreenModel extends ViewModel {
    private MutableLiveData<String> text;

    public LiveData<String> getString(){ // mục đích: cho phép activity lấy dữ liệu từ ViewModel
            if(text==null){ //nếu dữ liệu rỗng thì tạo một biến dữ liệu mới rồi gn giá trị "0"
               text = new MutableLiveData<>();
               text.setValue("0");
            }
            return text; // trả về dữ liệu LiveData theo nguyên tắc Đóng gói: UI chỉ được phép đọc dữ liệu
    }

    public void addString(String str){
        String s = text.getValue();
        if ("0".equals(s)){ // nếu giá trị hiện tại là 0
            text.setValue(str);
        }
        else
        {
            text.setValue(s + str);
        }
    }

    public void removeLast(){
        String s = text.getValue();
        if (s.length()==1){
            text.setValue("0");
        }
        else if (s.length()>0){
            text.setValue(s.substring(0, s.length()-1));//String sub = original.substring(beginIndex, endIndex);
            // trả về chuỗi con từ vị trí bắt đầu đến truước vị trí kết thúc
        }
    }

    public void reset(){
        text.setValue("");
    }
}
