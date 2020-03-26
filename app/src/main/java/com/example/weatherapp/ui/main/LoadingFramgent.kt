package com.example.weatherapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R

class LoadingFramgent : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        super.onActivityCreated(savedInstanceState)

        val args: LoadingFramgentArgs by navArgs()

        viewModel.getCityWeatherByName(args.cityName).observe(viewLifecycleOwner, Observer { city ->
            if (city == null) {
                findNavController().navigate(LoadingFramgentDirections.actionLoadingFramgentToMainFragment())
            } else {
                findNavController().navigate(LoadingFramgentDirections.actionLoadingFramgentToWeatherFragment())
            }
        })
    }
    companion object {
        fun newInstance() = LoadingFramgent()
    }
}
