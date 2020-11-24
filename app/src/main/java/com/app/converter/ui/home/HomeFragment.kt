package com.app.converter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.converter.databinding.FragmentHomeBinding
import com.app.converter.ui.convert.ConvertRateSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

  val homeViewModel: HomeViewModel by viewModel ()
  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentHomeBinding.inflate(inflater, container, false)
    val view = binding.root



    homeViewModel.getRates()
    binding.rvHomeRates.layoutManager = LinearLayoutManager(requireContext())

    homeViewModel.showAllRates.observe(viewLifecycleOwner, Observer {
          if (it!=null){
            val adapter = RatesAdapter(it)
            binding.rvHomeRates.adapter =adapter
            adapter.isItemClicked.observe(viewLifecycleOwner, Observer {item->
              if (item!=null){
                val dialog = ConvertRateSheet(homeViewModel.showBaseRate.value?:"",item.rate,item.value)
                dialog.show(requireActivity().supportFragmentManager,"Convert")
              }

            })
          }
    })

    homeViewModel.showBaseRate.observe(viewLifecycleOwner, Observer {
      if (!it.isNullOrEmpty()) {
        binding.tvPageTitle.text = it

      }
    })
    return view
  }
}