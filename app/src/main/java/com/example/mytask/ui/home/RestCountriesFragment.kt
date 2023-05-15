package com.example.mytask.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytask.R
import com.example.mytask.databinding.FragmentRestCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestCountriesFragment : Fragment(R.layout.fragment_rest_countries) {
    private  val TAG = "RestCountriesFragment"
    private lateinit var binding: FragmentRestCountriesBinding
    private val viewModel: RestCountriesViewModel by viewModels()
    private val restCountryAdapter: RestCountryAdapter by lazy {
        RestCountryAdapter(
            requireContext(),
            arrayListOf()
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRestCountriesBinding.bind(view)
        initViews()
        setObservers()
    }

    private fun initViews()= with(binding) {
        restCountriesListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        restCountriesListRv.adapter = restCountryAdapter
    }

    private fun setObservers()= with(viewModel) {
        restCountriesData.observe(viewLifecycleOwner){
            it?.let {list->
                restCountryAdapter.updateList(list)
            }
            /*when(it){
                is BaseResponse.Loading -> {
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }
                is BaseResponse.Success -> {
                    it.data?.let {list->
                        restCountryAdapter.updateList(list)
                    }
                }
                is BaseResponse.Error -> {
                    Log.e(TAG, "setObservers: ${it.message}", )
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }*/
        }
    }
}