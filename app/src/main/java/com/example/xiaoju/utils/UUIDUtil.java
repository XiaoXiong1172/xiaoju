package com.example.xiaoju.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        s = s.replace("-","");
        return s;
    }
    public static int getUUIDForInt(){
        return  UUID.randomUUID().hashCode();
    }

}
