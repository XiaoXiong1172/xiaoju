package com.example.xiaoju.ui.mv;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.xiaoju.data.AppDataSource;
import com.example.xiaoju.model.DataBean;

public class MeiNvViewModel extends ViewModel {
    AppDataSource dataSource = null;
    public MeiNvViewModel(){
        dataSource = new AppDataSource();
    }
    public LiveData<PagedList<DataBean>> getDataBean(String title) {
        Log.d("tag", "美女图片请求");
        return dataSource.getDataBean(title);
    }
}
