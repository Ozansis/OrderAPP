package com.example.orderapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapp.repo.FoodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel  @Inject constructor (val repo : FoodRepo) : ViewModel() {







     fun addCartItem(yemek_adi: String,
                     yemek_resim_adi: String,
                     yemek_fiyat: Int,
                     yemek_siparis_adet :Int,
                     kullanici_adi :String)
     {
        viewModelScope.launch {

            repo.addCartItem(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)


        }
    }




}