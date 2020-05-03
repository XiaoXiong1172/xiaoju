package com.example.xiaoju.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetApi {
    public static final String BASEURL = "http://api.tianapi.com";
    public static final String BASEURL2 = "http://192.168.1.103:8080";
    public static final String KEY = "f7655da54f117d3df607cbb4558bb284";
    private static Retrofit retrofit;
    private static Retrofit retrofit2;

    private static Retrofit retrofit(){
       synchronized (NetApi.class){
           if (retrofit == null){
               HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
               logger.setLevel(HttpLoggingInterceptor.Level.BASIC);
               OkHttpClient okHttpClient = new OkHttpClient()
                       .newBuilder()
                       .callTimeout(20,TimeUnit.SECONDS)
                       .readTimeout(20,TimeUnit.SECONDS)
                       .addInterceptor(logger)
                       .build();
               retrofit = new Retrofit.Builder()
                       .client(okHttpClient)
                       .addConverterFactory(GsonConverterFactory.create())
                       .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                       .baseUrl(BASEURL)
                       .build();
           }
           return retrofit;
       }
    }
    private static Retrofit retrofit2(){
        synchronized (NetApi.class){
            if (retrofit2 == null)
                retrofit2 = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(BASEURL2)
                        .build();
            return retrofit2;
        }
    }


    public static ServiceApi getApi(){
       return retrofit().create(ServiceApi.class);
    }
    public  static ServiceApi2 getApi2(){
        return retrofit2().create(ServiceApi2.class);
    }
}
