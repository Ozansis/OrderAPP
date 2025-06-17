package com.example.orderapp.retrofit

import com.example.orderapp.data.entity.CRUDResponse
import com.example.orderapp.data.entity.CartResponse
import com.example.orderapp.data.entity.FoodResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {
    // http://kasimadalan.pe.hu --> Base Url
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //yemekler/tumYemekleriGetir.php --> web servis
    //http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php
    //http://kasimadalan.pe.hu/yemekler/sepettekiYemekleriGetir.php
    //http://kasimadalan.pe.hu/yemekler/sepettenYemekSil.php
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadFood() : FoodResponse

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun addCartItem(@Field("yemek_adi") yemek_adi: String,
                            @Field("yemek_resim_adi") yemek_resim_adi: String,
                            @Field("yemek_fiyat") yemek_fiyat: Int,
                            @Field("yemek_siparis_adet") yemek_siparis_adet :Int,
                           @Field("kullanici_adi") kullanici_adi :String) : CRUDResponse


    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun loadCartItem(@Field("kullanici_adi") kullanici_adi: String) : CartResponse


    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun deleteFood(@Field("sepet_yemek_id") sepet_yemek_id : Int,
                           @Field("kullanici_adi") kullanici_adi: String) : CRUDResponse

}