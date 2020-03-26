package com.example.weatherapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.fragment_weather.*
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class WeatherFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(WeatherFragmentDirections.actionWeatherFragmentToMainFragment(false))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: WeatherFragmentArgs by navArgs()
        val cityWeather = args.cityWeatherData

        Log.e("DUPA", cityWeather.toString())

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")
        val currentDateTime = LocalDateTime.now().format(formatter)

        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val sunrise = LocalDateTime.ofInstant(Instant.ofEpochMilli(cityWeather.sys.sunrise.toLong()), ZoneId.systemDefault())
            .format(timeFormatter)
        val sunset = LocalDateTime.ofInstant(Instant.ofEpochMilli(cityWeather.sys.sunset.toLong()), ZoneId.systemDefault())
            .format(timeFormatter)

        cityNameText.text = cityWeather.name
        dateTimeText.text = currentDateTime
        temperatureText.text = cityWeather.main.temp.toString() + "°C"
        airPressureText.text = cityWeather.main.pressure.toString() + " hPa"
        sunriseTimeText.text = sunrise
        sunsetTimeText.text = sunset
        weatherDescText.text = cityWeather.weather[0].description

        when (cityWeather.weather[0].main) {
            "Clear" -> weatherImage.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.ic_clear))
            "Clouds" -> weatherImage.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.ic_clouds))
            "Atmosphere" -> weatherImage.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.ic_atmosphere))
            "Snow" -> weatherImage.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.ic_snow))
            "Rain" -> weatherImage.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.ic_rain))
            "Drizzle" -> weatherImage.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.ic_drizzle))
            "Thunderstorm" -> weatherImage.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.ic_thunderstorm))
        }
    }

    companion object {
        fun newInstance() = WeatherFragment()
    }
}
