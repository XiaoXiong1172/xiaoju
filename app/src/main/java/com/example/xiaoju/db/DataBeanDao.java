package com.example.xiaoju.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.xiaoju.model.DataBean;


@Dao
public interface DataBeanDao {
    @Query("delete from datanews where type = :par1")
    int deleteAll(String par1);
    @Delete
    int deleteById(DataBean bean);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DataBean bean);

    //order by id,ctime collate nocase desc;
    @Query("select * from datanews where type =  :par1 ")
    DataSource.Factory<Integer, DataBean> selectAllByType(String par1);
}
