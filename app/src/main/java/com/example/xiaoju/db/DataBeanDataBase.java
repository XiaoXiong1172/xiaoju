package com.example.xiaoju.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.xiaoju.model.DataBean;


@Database(entities = {DataBean.class},version = 1)
public abstract  class DataBeanDataBase extends RoomDatabase {
    public abstract DataBeanDao dataBeanDao();
}
