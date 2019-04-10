package com.library.common.api;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 日期：2019/3/9 14:57
 * 创建：李加蒙
 * 描述：
 */
public interface IApiServer {
    @GET("user/login")
    Observable<String> LoginByRx(@Query("username") String username, @Query("password") String password);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

    @POST("http://192.168.0.86:13002/dfs/upload")
    @Multipart
    Observable<String> upLoadFile(@Part MultipartBody.Part[] parts, @Part("token") RequestBody token);


//    @FormUrlEncoded
//    @GET("user/login")
//    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);

//    @FormUrlEncoded
//    @POST("user/login")
//    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);
}
