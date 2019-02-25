package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/insert.php")
    Call<Value> mhs(@Field("nim") String nim,
                    @Field("nama") String nama,
                    @Field("jenisKelamin") String jenisKelamin,
                    @Field("prodi") String prodi
    );

    @GET("/view.php")
    Call<Value> view();
}
