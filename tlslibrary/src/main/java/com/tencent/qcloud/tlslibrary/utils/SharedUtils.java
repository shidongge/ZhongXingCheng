package com.tencent.qcloud.tlslibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shido on 2017/3/13.
 */

public class SharedUtils {
    private String name = "fitst";
    public  void saveShared(String key, String value, Context ctx){
        SharedPreferences shared = ctx.getSharedPreferences(name ,0);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString(key,value);
        edit.commit();
    }
    public String  getShared(String key,Context ctx){
        String str = null;
        SharedPreferences shared = ctx.getSharedPreferences(name, 0);
        str = shared.getString(key,"");
        return str;
    }
    public void qingchu(Context ctx){
        SharedPreferences shared = ctx.getSharedPreferences(name ,0);
        SharedPreferences.Editor editor = shared.edit();
        editor.clear();
        editor.commit();
    }




    private String token = "token";
    public  void saveToken(String key, String value, Context ctx){
        SharedPreferences shared = ctx.getSharedPreferences(token ,0);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString(key,value);
        edit.commit();
    }
    public String  getToken(String key,Context ctx){
        String str = null;
        SharedPreferences shared = ctx.getSharedPreferences(token, 0);
        str = shared.getString(key,"");
        return str;
    }
}
