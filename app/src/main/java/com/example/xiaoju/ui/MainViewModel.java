package com.example.xiaoju.ui;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.xiaoju.data.AppDataSource;
import com.example.xiaoju.ui.gj.GuoJiFragment;
import com.example.xiaoju.ui.sh.SheHuiFragment;
import com.example.xiaoju.ui.zh.ZongHeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MainViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";
    private static MutableLiveData<List<Fragment>> data;

    public MainViewModel() {
        data = new MutableLiveData<>();
    }
    public MutableLiveData<List<Fragment>> getData() {
        update();
        return data;
    }

    private void update(){
       List<Fragment> temp = new ArrayList<>();
       temp.add(ZongHeFragment.newInstance(AppDataSource.ZHXW));
       temp.add(SheHuiFragment.newInstance(AppDataSource.SHXW));
       temp.add(GuoJiFragment.newInstance(AppDataSource.GJXW));
        data.setValue(temp);
    }
}
