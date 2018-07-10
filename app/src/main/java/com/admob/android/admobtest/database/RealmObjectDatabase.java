package com.admob.android.admobtest.database;

import io.realm.RealmObject;

public class RealmObjectDatabase extends RealmObject{
    private String TAG;
    private String name;
    private String data;

    public RealmObjectDatabase() {
    }

    public RealmObjectDatabase(String TAG, String name, String data) {
        this.TAG = TAG;
        this.name = name;
        this.data = data;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RealmObjectDatabase{" +
                "TAG='" + TAG + '\'' +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
