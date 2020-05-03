package com.example.xiaoju.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerUtil {
    private static ExecutorService fixedThreadPool;
    public static ExecutorService getWorler(){
        if (fixedThreadPool == null)
        fixedThreadPool = Executors.newFixedThreadPool(5);
        return fixedThreadPool;
    }

}
