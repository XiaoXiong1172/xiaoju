package com.example.xiaoju;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class App extends Application {
    private static Context context;
    private OkHttpClient client = new OkHttpClient();
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化必备组件
        //子线程请求网络，保存图片到指定位置
     new Thread(new Runnable() {
             String string = "";
            @Override
            public void run() {
                getUrl();
            }
            private void getPic() {
                    if (!string.equals("")){
                        Request request = new Request.Builder().get().url(string).build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                 Bitmap b =  BitmapFactory.decodeStream(response.body().byteStream());
                                File file = getCacheDir();
                                File file1 = new File(file,"img/");
                                if (!file1.exists()){
                                    file1.mkdir();
                                }
                                File file2 = new File(file1,"index.jpeg");
                                FileOutputStream out = new FileOutputStream(file2);
                            b.compress(Bitmap.CompressFormat.JPEG,90,out);
                            out.flush();
                            out.close();
                            }
                        });
                    }
            }

            private void getUrl() {
                Request request = new Request.Builder().get().url("http://guolin.tech/api/bing_pic").build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                            string = response.body().string();
                        Log.d(TAG, "onResponse: "+string);
                        getPic();
                    }
                });
            }
        }).start();
    }

    public static Context getCtx(){
        return context;
    }

    private static final String TAG = "App";
}
