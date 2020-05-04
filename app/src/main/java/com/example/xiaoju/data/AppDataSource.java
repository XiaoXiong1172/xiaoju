package com.example.xiaoju.data;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.xiaoju.db.AppDataCache;
import com.example.xiaoju.model.DataBean;
import com.example.xiaoju.model.DataBeanResult;
import com.example.xiaoju.ui.gj.GuoJiFragment;
import com.example.xiaoju.ui.mv.MeiNvFragment;
import com.example.xiaoju.ui.sh.SheHuiFragment;
import com.example.xiaoju.ui.zh.ZongHeFragment;


public class AppDataSource {

    public static final String ZHXW = "综合新闻";
    // cid = 5
    public static final String SHXW = "社会新闻";
    // cid = 8
    public static final String GJXW = "国际新闻";
    //cid = 7
    public static final String GNXW = "国内新闻";
    //cid = 11
    public static final String MNTP = "美女图片";
    //cid = 10
    public static final String YLXW = "娱乐新闻";
    //cid = 13
    public static final String KJXW = "科技新闻";
    //cid = 27
    public static final String JSXW = "军事新闻";
    //cid = 18
    public static final String LYZX = "旅游咨询";
    //cid = 17
    public static final String JKZS = "健康知识";
    //cid = 38
    public static final String HFXW = "汉服新闻";


    public static final String[] TITLE = {
    ZHXW,SHXW ,GJXW,GNXW ,MNTP ,YLXW , KJXW ,JSXW ,LYZX , JKZS ,HFXW

    };

    public static final Fragment[] FRAGMENTS = {
        ZongHeFragment.newInstance(ZHXW),
            SheHuiFragment.newInstance(SHXW),
            GuoJiFragment.newInstance(GJXW),
            GuoJiFragment.newInstance(GNXW),
            MeiNvFragment.newInstance(MNTP),
            GuoJiFragment.newInstance(YLXW),
            GuoJiFragment.newInstance(KJXW),
            GuoJiFragment.newInstance(JSXW),
            GuoJiFragment.newInstance(LYZX),
            GuoJiFragment.newInstance(JKZS),
            GuoJiFragment.newInstance(HFXW)
    };

    public LiveData<PagedList<DataBean>> getDataBean(String title) {
        DataSource.Factory<Integer, DataBean> dataBeanFactory = AppDataCache.getInstance().getDataBean(title);
        DatabeanCallback databeancallback = new DatabeanCallback(title);
        LiveData<PagedList<DataBean>> data = new LivePagedListBuilder<>(dataBeanFactory,10)
                .setBoundaryCallback(databeancallback)
                .build();
        return data;
    }
}
