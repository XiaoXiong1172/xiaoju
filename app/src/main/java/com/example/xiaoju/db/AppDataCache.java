package com.example.xiaoju.db;

import android.util.Log;

import androidx.paging.DataSource;


import com.example.xiaoju.api.NetApi;
import com.example.xiaoju.data.AppDataSource;
import com.example.xiaoju.model.DataBean;
import com.example.xiaoju.model.RootBean;
import com.example.xiaoju.utils.AppConfig;
import com.example.xiaoju.utils.DBUtil;
import com.example.xiaoju.utils.WorkerUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppDataCache {

    private final static int PAGESIZE = 20;

//    String title, String ctime, String description, String picUrl, String url, String type

    private static final String TAG = "AppDataCache";
    private Callback callback;
    private String title;
    private AppDataCache(){}
    public static AppDataCache instance;
    public static AppDataCache getInstance(){
        if (instance == null){
            synchronized (AppDataCache.class){
                instance = new AppDataCache();
            }
        }
        return instance;
    }

    private void saveDataBean(String title, List<DataBean> dataBeans, Callback callback){
        WorkerUtil.getWorler().execute(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (DataBean d :
                        dataBeans) {
                    d.setType(title);
                    count += DBUtil.getAppDatabase().dataBeanDao().insert(d);
                }
                callback.comp(count);
            }
        });
    }

    public DataSource.Factory<Integer, DataBean> getDataBean(String title){
        return  DBUtil.getAppDatabase().dataBeanDao().selectAllByType(title);
    }

    public void RequestNet(final String title,Callback callback){
        Log.d(TAG, "RequestNet: "+title);

        switch (title){
            case AppDataSource
                    .SHXW:
                requestFLXW(5,title,callback);
            break;
            case AppDataSource.GJXW:
                requestFLXW(8,title,callback);
                break;
            case AppDataSource.ZHXW:
                requestZHXW(title,callback);
                break;
        }
    }

   private Observer<RootBean<DataBean>> observer =  new Observer<RootBean<DataBean>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(RootBean<DataBean> dataBeanRootBean) {
           if (dataBeanRootBean.code == 200){
               callback.success();
               saveDataBean(title,dataBeanRootBean.newslist,callback);
           }else {
               callback.error(dataBeanRootBean.msg);
           }
        }

        @Override
        public void onError(Throwable e) {
            callback.error(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    };

    private void requestFLXW(final int cid, String title, Callback callback) {
        this.callback = callback;
        this.title = title;
        NetApi.getApi().getFLXW(AppConfig.KEY,PAGESIZE,cid,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void requestZHXW(String title, Callback callback) {
        this.callback = callback;
        this.title = title;
        NetApi.getApi().getGeneralNews(AppConfig.KEY,PAGESIZE,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void deleteDataBean(DataBean dataBean) {
        WorkerUtil.getWorler().execute(new Runnable() {
            @Override
            public void run() {
             int i  = DBUtil.getAppDatabase().dataBeanDao().deleteById(dataBean);
                Log.d(TAG, "delete databean "+dataBean.getId()+",count = "+i);
            }
        });
    }
}
