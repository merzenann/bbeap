package com.example.merzensumagaysay.bbeap;

/**
 * Created by merzensumagaysay on 24/10/2018.
 */

public class APIUtils {

    private APIUtils()
    {
    };


    public static final String API_URL = "http://10.90.205.174:8080/admin/";

    public static SafeService getSafeService(){
        return RetrofitClient.getClient(API_URL).create(SafeService.class);
    }

}
