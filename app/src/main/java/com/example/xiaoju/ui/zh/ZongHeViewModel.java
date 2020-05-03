package com.example.xiaoju.ui.zh;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.xiaoju.data.AppDataSource;
import com.example.xiaoju.model.DataBean;
import com.example.xiaoju.model.DataBeanResult;

public class ZongHeViewModel extends ViewModel {
    AppDataSource dataSource = null;
    public ZongHeViewModel(){
        dataSource = new AppDataSource();
    }
    public LiveData<PagedList<DataBean>> getDataBean(String title) {
        Log.d("tag", "综合新闻请求");
        return dataSource.getDataBean(title);
    }

}
