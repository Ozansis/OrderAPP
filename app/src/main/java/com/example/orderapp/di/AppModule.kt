package com.example.orderapp.di

import com.example.orderapp.data.datasource.FoodDataSource
import com.example.orderapp.repo.FoodRepo
import com.example.orderapp.retrofit.ApiUtils
import com.example.orderapp.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Provides
//    @Singleton
//    fun provideFoodDataSource(foodDao: FoodDao) : FoodDataSource{
//
//        return FoodDataSource(foodDao)
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideFoodRepository(fds : FoodDataSource) : FoodRepo{
//
//        return FoodRepo(fds)
//    }

    @Provides
    @Singleton
    fun provideFoodDao() : FoodDao{

        return ApiUtils.getFoodDao()
    }



}