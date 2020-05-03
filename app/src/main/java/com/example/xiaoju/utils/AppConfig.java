package com.example.xiaoju.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.xiaoju.App;


/**
 * app参数
 */
public class AppConfig {
    public static final String KEY = "f7655da54f117d3df607cbb4558bb284";
    public static final String QQKEY = "101868033";
//    public static final String QQKEY = "39ea66e5515e86be17c3db67806b2873";
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_INITAL_LOAD_SIZE_HINT = 10;
    private static final long DEFAULT_END_TIME = System.currentTimeMillis();
        private static final long OFFSETTIME = 48 * 3600 * 1000;

    private static AppConfig appConfig;
    private static SharedPreferences sp;
    private AppConfig(){
        sp = App.getCtx().getSharedPreferences("appconfig", Context.MODE_PRIVATE);
    }
    public static AppConfig getInstence() {
        if (appConfig == null)
            appConfig = new AppConfig();
        return appConfig;
    }
    /**
     * 分页大小
     * @return 每页个数
     */
    public int getPageSize() {
        return sp.getInt("pagesize",DEFAULT_PAGE_SIZE);
    }
    /**
     * 初始化数量
     * @return 初始化数量
     */
    public int getInitalLoadSizeHint() {
        return sp.getInt("initalloadsizehint",DEFAULT_INITAL_LOAD_SIZE_HINT);
    }

    /**
     * 禁用加载到期时间
     * @return
     */
    public long getEndTime(){
       return sp.getLong("endtime",DEFAULT_END_TIME);
    }

    /**
     * 延迟加载时间位移
     * @return
     */
    public long getOffsetTime() {
        return sp.getLong("offsettime",OFFSETTIME);
    }

    public int getNewItemsCount(){
        return sp.getInt("newitemcount",0);
    }

    public void setNewItemCount(int count){
        sp.edit().putInt("newitemcount",count).apply();
    }

    public void setConfigInt(String key,int value){
        sp.edit().putInt(key,value).apply();
    }
    public void setConfigString(String key,String value){
        sp.edit().putString(key, value).apply();
    }
    public void setConfigLong(String key,long value){
        sp.edit().putLong(key,value).apply();
    }


}
