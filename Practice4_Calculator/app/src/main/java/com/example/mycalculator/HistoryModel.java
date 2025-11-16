package com.example.mycalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

public class HistoryModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> history;

    public LiveData<ArrayList<String>> getHistory(){
            if (history==null){
               history = new MutableLiveData<>();
               history.setValue(new ArrayList<>());
            }
            return history;
    }
    public void addHistory (String equation){ // muốn thêm một mảng vào trong mot lịch sử thì phải
        ArrayList<String> h = history.getValue();
        h.add(equation);
        history.setValue(h);
    }

    public void clear(){
        history.setValue(new ArrayList<>());
    }

}
