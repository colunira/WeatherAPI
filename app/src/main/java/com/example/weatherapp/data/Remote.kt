package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.City
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Remote {
    private val baseURL = "http://api.openweathermap.org/data/2.5/"
    private val retrofit = Retrofit.Builder().baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    private val weatherAPI = retrofit.create(WeatherAPI::class.java)
    private val appID = "dfdb49a72d79d0d239ddb725ba309a70"

    fun getCityWeatherByName(cityName: String): LiveData<City> {
        val responseWeather = MutableLiveData<City>()
        val call = weatherAPI.getCityWeatherByName(cityName, appID)

        call.enqueue(object: Callback<City> {
            override fun onResponse(call: Call<City>, response: Response<City>) {
                if (response.isSuccessful) {
                    responseWeather.value = response.body()
                } else {
                    responseWeather.value = null
                }
            }
            override fun onFailure(call: Call<City>, t: Throwable) {
                responseWeather.value = null
            }
        })
        return responseWeather
    }
}