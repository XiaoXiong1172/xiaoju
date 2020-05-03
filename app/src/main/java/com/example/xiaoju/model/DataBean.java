package com.example.xiaoju.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "datanews")
public class DataBean {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private long id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String ctime;

    private String description;
    private String picUrl;
    private String url;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ColumnInfo
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    public DataBean( String title, String ctime, String description, String picUrl, String url, String type) {
        this.title = title;
        this.ctime = ctime;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ctime='" + ctime + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
