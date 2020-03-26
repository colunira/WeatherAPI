package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import kotlinx.android.synthetic.main.main_fragment.view.city_edit_text
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        view.next_button.setOnClickListener {
            if (!isNameValid((city_edit_text.text!!))) {
                city_text_input.error = getString(R.string.city_name_error)
            } else {
                city_text_input.error = null
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToLoadingFragment(view.city_edit_text.text.toString()))
            }
        }

        view.city_edit_text.setOnKeyListener { v, keyCode, event ->
            city_text_input.error = null
            errorLoading.visibility = View.INVISIBLE
            false
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: MainFragmentArgs by navArgs()

        if (args.isCallFailed) {
            errorLoading.visibility = View.VISIBLE
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun isNameValid(text: Editable?): Boolean {
        return text!!.isNotEmpty()
    }
}
