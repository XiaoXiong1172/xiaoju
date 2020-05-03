package com.example.xiaoju.api;


import com.example.xiaoju.model.DataBean;
import com.example.xiaoju.model.RootBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
//    @GET("/channellist/index")
//    Call<RootBean<NewItems>> getNewsItem(@Query("key") String key);
    @GET("/generalnews/index")
    Observable<RootBean<DataBean>> getGeneralNews(@Query("key") String key, @Query("num") int num, @Query("rand") int rand);

    @GET("/allnews/index")
    Observable<RootBean<DataBean>> getFLXW(@Query("key") String key, @Query("num") int num, @Query("col") int col, @Query("rand") int rand);

    //@GET("/allnews/index")
    //Observable<RootBean<GeneralNews>> SheHuiNews(@Query("key") String key, @Query("num") int num, @Query("rand") int rand, @Query("col") int col);
}
