package com.example.merzensumagaysay.bbeap.blueprint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by merzensumagaysay on 24/10/2018.
 */

public interface SafeService {


    @Headers("content-type:application/json")
    @GET("fetch.php/") //user
    Call<List<SafeExits>> getExit();

  //  @FormUrlEncoded //admin
    @Headers("content-type:application/json")
    @POST("up.php/{exitID}")
    Call<JSONResponse> updateExit(@Field("iStatus") int iStatus,
                           @Path("exitID") int exitID);


}