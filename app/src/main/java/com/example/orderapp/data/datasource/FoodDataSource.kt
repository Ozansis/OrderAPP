package com.example.orderapp.data.datasource

import com.example.orderapp.data.entity.CRUDResponse
import com.example.orderapp.data.entity.CartFood
import com.example.orderapp.data.entity.Food
import com.example.orderapp.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Field
import javax.inject.Inject

class FoodDataSource @Inject constructor(private val fdao: FoodDao) {


    suspend fun loadFoods() : List<Food> =
        withContext(Dispatchers.IO) { return@withContext fdao.loadFood().yemekler }



    suspend fun addCartItem(yemek_adi: String,
                            yemek_resim_adi: String,
                            yemek_fiyat: Int,
                            yemek_siparis_adet :Int,
                            kullanici_adi :String) {

        fdao.addCartItem(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }


    suspend fun cartLoadFoods(name : String) : List<CartFood> =
        withContext(Dispatchers.IO) { return@withContext fdao.loadCartItem(name).sepet_yemekler }



    suspend fun deleteFood(sepet_yemek_id : Int, kullanici_adi: String)
    {

        fdao.deleteFood(sepet_yemek_id,kullanici_adi)

    }


}