package com.example.xiaoju.ui.gj;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.xiaoju.data.AppDataSource;
import com.example.xiaoju.model.DataBean;

public class GuoJiViewModel extends ViewModel {
    AppDataSource dataSource = null;
    public GuoJiViewModel(){
        dataSource = new AppDataSource();
    }
    public LiveData<PagedList<DataBean>> getDataBean(String title) {
        Log.d("tag", "国际新闻请求");
        return dataSource.getDataBean(title);
    }
}
