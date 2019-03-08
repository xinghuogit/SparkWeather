package com.sparkweather.base;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.cache.CacheInterceptor;

/**
 * 日期：2019/3/8 17:29
 * 创建：李加蒙
 * 描述：
 */
public class HttpHelper extends IDataHelper {
    private Context context;

    public HttpHelper(Context context) {
        super();
        this.context = context;
    }

//    public OkHttpClient getOkHttpClient() {
//        ClearableCookieJar cookieJar = //对cooke自动化管理
//                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
//        File cacheFile = new File(context.getExternalCacheDir(), "SparkWeather_Cache");//缓存路径
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 40);//缓存大小 40M
//        CacheInterceptor cacheInterceptor = new CacheInterceptor(context);//获取缓存
//        return
//    }
}
