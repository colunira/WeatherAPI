package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import com.example.weatherapp.model.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {
    @GET("weather/?units=metric")
    fun getCityWeatherByName(@Query("q") name: String, @Query("appid") appID: String): Call<City>
}