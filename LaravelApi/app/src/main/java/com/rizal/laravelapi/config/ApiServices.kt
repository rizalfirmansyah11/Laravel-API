package com.rizal.laravelapi.config

import android.telecom.Call
import com.rizal.laravelapi.model.ResponseListMahasiswa
import com.rizal.laravelapi.model.SubmitMahasiswa
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {

    @GET("mahasiswa")
    fun getMahasiswa(): retrofit2.Call<ResponseListMahasiswa>

    @GET("carimahasiswa")
    fun cariMahasiswa(@Query("cari") terms:String?): retrofit2.Call<ResponseListMahasiswa>

    @FormUrlEncoded
    @POST("mahasiswa")
    fun postMahasiswa(
        @Field("namamahasiswa") namamahasiswa: String,
        @Field("nim") nim: String,
        @Field("alamat") alamat: String,
        @Field("gender") gender: String,
        @Field("agama") agama: String,
        @Field("usia") usia: String,

        ): retrofit2.Call<SubmitMahasiswa>
}