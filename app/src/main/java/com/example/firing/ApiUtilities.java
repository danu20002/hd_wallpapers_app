package com.example.firing;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private  static   Retrofit retrofit=null;
    public   static final String API="po9zSxXUh8TlsWvENuiiUYy3VA7wLC21OJMAHNsNdPHjXxPpqr9nE5CL";

    public static ApiInterface getApiInterface(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit.create(ApiInterface.class);
    }
}
