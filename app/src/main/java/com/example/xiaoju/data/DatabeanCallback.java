package com.example.xiaoju.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.xiaoju.db.AppDataCache;
import com.example.xiaoju.db.Callback;
import com.example.xiaoju.model.DataBean;
import com.example.xiaoju.ui.dialog.DialogManager;


public class DatabeanCallback  extends PagedList.BoundaryCallback<DataBean> {
    private  boolean isload = false;
    private String title;
    private int i = 0;
    private  int count = 0;
    private MutableLiveData<String> data = new MutableLiveData<>();
    public DatabeanCallback(String title) {
        super();
        this.title = title;
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        Log.d("tag", "onZeroItemsLoaded: "+count++);
//        AlertDialog dialog = DialogManager.getProgressDialog();
//        dialog.show();
        loadData(new Callback() {
            @Override
            public void success() {
                Log.d(TAG, "success: ");
            }

            @Override
            public void error(String msg) {
              data.setValue(msg);
//              dialog.dismiss();
            }

            @Override
            public void comp(int count) {
                Log.d(TAG, "comp: "+count);
//                dialog.dismiss();
            }
        });
    }

    private static final String TAG = "DatabeanCallback";
    private void loadData(Callback callback) {
        Log.d("download", "loadData: "+isload);
        if (isload)return;
        isload = true;
        Log.d("download", "loadData: "+isload);
        AppDataCache.getInstance().RequestNet(title, new Callback() {
            @Override
            public void success() {
               callback.success();
            }

            @Override
            public void error(String msg) {
               callback.error(msg);
                data.setValue(msg);
            }

            @Override
            public void comp(int count) {
                Log.d("tag", "comp: count = "+count);
                if (count >0){
                    isload = false;
                }
                callback.comp(count);
            }
        });
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull DataBean itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull DataBean itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        Log.d("tag", "onItemAtEndLoaded: ");
        loadData(new Callback() {
            @Override
            public void success() {
                Log.d("tag", "success: ");

            }

            @Override
            public void error(String msg) {
                data.setValue(msg);

            }

            @Override
            public void comp(int count) {
                Log.d("tag", "comp: ");
            }
        });
    }
}
