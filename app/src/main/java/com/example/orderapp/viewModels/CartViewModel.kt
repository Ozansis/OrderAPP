package com.example.orderapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapp.data.entity.CartFood
import com.example.orderapp.repo.FoodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor (val repo: FoodRepo): ViewModel() {

    private val _cartFood = MutableLiveData<List<CartFood>>()
    val cartFood : LiveData<List<CartFood>> =_cartFood

    init {
        loadCartFood("Ozanssmngl")
    }

     fun loadCartFood(name: String){

        viewModelScope.launch {
            try {
                _cartFood.value=repo.chartLoadFoods(name)
            } catch (e: Exception) {

            }


        }


    }

     fun deleteFood(sepet_yemek_id : Int, kullanici_adi: String){

        viewModelScope.launch {

            try {
                repo.deleteFood(sepet_yemek_id,kullanici_adi)

                loadCartFood("Ozanssmngl")
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }

        }

    }

    fun cleanCart(username:String){


        cartFood.value?.forEach { item ->
            deleteFood(item.sepet_yemek_id.toInt(),username)
        }


    }





}