package com.example.xiaoju.data;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.xiaoju.db.AppDataCache;
import com.example.xiaoju.model.DataBean;
import com.example.xiaoju.model.DataBeanResult;
import com.example.xiaoju.ui.sh.SheHuiFragment;
import com.example.xiaoju.ui.zh.ZongHeFragment;


public class AppDataSource {

    public static final String ZHXW = "综合新闻";
    // cid = 5
    public static final String SHXW = "社会新闻";
    // cid = 8
    public static final String GJXW = "国际新闻";

    public LiveData<PagedList<DataBean>> getDataBean(String title) {
        DataSource.Factory<Integer, DataBean> dataBeanFactory = AppDataCache.getInstance().getDataBean(title);
        DatabeanCallback databeancallback = new DatabeanCallback(title);
        LiveData<PagedList<DataBean>> data = new LivePagedListBuilder<>(dataBeanFactory,10)
                .setBoundaryCallback(databeancallback)
                .build();
        return data;
    }
}
