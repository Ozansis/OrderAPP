package com.example.orderapp.repo

import com.example.orderapp.data.entity.Food
import com.example.orderapp.data.datasource.FoodDataSource
import com.example.orderapp.data.entity.CartFood
import javax.inject.Inject

class FoodRepo @Inject constructor(var fds: FoodDataSource) {


    suspend fun loadFoods() : List<Food> = fds.loadFoods()



    suspend fun addCartItem(yemek_adi: String,
                            yemek_resim_adi: String,
                            yemek_fiyat: Int,
                            yemek_siparis_adet :Int,
                            kullanici_adi :String) {

        fds.addCartItem(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }

    suspend fun chartLoadFoods(name : String) : List<CartFood> =fds.cartLoadFoods(name)


    suspend fun deleteFood(sepet_yemek_id : Int, kullanici_adi: String)  =
        fds.deleteFood(sepet_yemek_id,kullanici_adi)



}