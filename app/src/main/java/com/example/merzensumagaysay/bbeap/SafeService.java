package com.example.merzensumagaysay.bbeap;

import java.text.MessageFormat;
import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by merzensumagaysay on 24/10/2018.
 */

public interface SafeService {


    @Headers("content-type:application/json")
    @GET("fetch.php/") //user
    Call<List<SafeExits>> getExit();

    @FormUrlEncoded
    @POST("upd.php/") //admin
    Call<ResponseBody> updateExit(@Field("exitID") int exitID,
                                  @Field("iStatus") int iStatus);

    @Headers("content-type:application/json")
    @GET("get.php/") //message
    Call<List<SafeExits>> getMessage();

    @FormUrlEncoded
    @POST("ins.php/") //message
    Call<String> sendMessage(@Field("instruction") String instruction);




}
