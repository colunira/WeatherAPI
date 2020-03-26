package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.Remote
import com.example.weatherapp.model.City

class MainViewModel : ViewModel() {
    private val remote = Remote()

    fun getCityWeatherByName(city: String): LiveData<City> {
        return remote.getCityWeatherByName(city)
    }
}
