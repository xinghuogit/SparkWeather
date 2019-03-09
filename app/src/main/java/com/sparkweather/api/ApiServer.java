package com.sparkweather.api;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 日期：2019/3/9 14:57
 * 创建：李加蒙
 * 描述：
 */
public interface ApiServer {

    @GET("user/login")
    Observable<String> LoginByRx(@Query("username") String username, @Query("password") String password);

//    @FormUrlEncoded
//    @GET("user/login")
//    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);

//    @FormUrlEncoded
//    @POST("user/login")
//    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);
}
