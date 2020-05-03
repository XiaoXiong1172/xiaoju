package com.example.xiaoju.model;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class DataBeanResult {
    private LiveData<PagedList<DataBean>> data;
    private LiveData<String> networkErrors;

    public DataBeanResult(LiveData<PagedList<DataBean>> data, LiveData<String> networkErrors) {
        this.data = data;
        this.networkErrors = networkErrors;
    }

    public LiveData<PagedList<DataBean>> getData() {
        return data;
    }

    public void setData(LiveData<PagedList<DataBean>> data) {
        this.data = data;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }

    public void setNetworkErrors(LiveData<String> networkErrors) {
        this.networkErrors = networkErrors;
    }
}
