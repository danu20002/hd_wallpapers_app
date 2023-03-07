package com.example.firing;

import static com.example.firing.ApiUtilities.API;

import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL="https://api.pexels.com/v1/";
    @Headers("Authorization:"+ API)
    @GET("curated")
    Call<searchModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );
    @Headers("Authorization:"+ API)
    @GET("search")
    Call<searchModel> getSearchImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}
