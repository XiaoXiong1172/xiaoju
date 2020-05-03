package com.example.xiaoju.db;

public interface Callback{
    void success();
    void error(String msg);
    void comp(int count);
}
