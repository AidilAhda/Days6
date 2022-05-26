package com.example.days6.rest;

import com.example.days6.response.ResponeLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponeLogin> login(
        @Field("username") String username,
        @Field("password") String password
    );
    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponeLogin> signup(
        @Field("username") String username,
        @Field("password") String password,
        @Field("nama_lengkap") String namaLengkap,
        @Field("no_hp") String noHp,
        @Field("email") String email
    );


}
