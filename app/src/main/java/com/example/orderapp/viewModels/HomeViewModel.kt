package com.example.orderapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapp.data.entity.Food
import com.example.orderapp.repo.FoodRepo
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repo : FoodRepo) : ViewModel() {
    private val _food = MutableLiveData<List<Food>>()
    val food : LiveData<List<Food>> =_food

    init{
        loadFoods()

    }

    private fun loadFoods(){
        viewModelScope.launch {

            _food.value = repo.loadFoods()

        }




    }



}