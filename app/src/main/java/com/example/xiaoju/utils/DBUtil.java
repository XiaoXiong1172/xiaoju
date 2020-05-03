package com.example.xiaoju.utils;

import androidx.room.Room;

import com.example.xiaoju.App;
import com.example.xiaoju.db.DataBeanDataBase;


public class DBUtil {
    public static DataBeanDataBase appDatabase;

    public static DataBeanDataBase getAppDatabase() {
        synchronized (DBUtil.class){
            if (appDatabase == null){

                appDatabase =  Room.databaseBuilder(App.getCtx(),DataBeanDataBase.class,"appdatabase")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return appDatabase;
        }
    }
}
