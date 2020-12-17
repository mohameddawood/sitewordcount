package com.app.instawordcount.ui.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.instawordcount.R
import com.app.instawordcount.databinding.FragmentHomeBinding
import com.app.instawordcount.manager.base.BaseApplication
import com.app.instawordcount.manager.utilities.Constants
import com.app.instawordcount.manager.utilities.checkIfInternetAvailable
import com.app.instawordcount.ui.resource_layout.no_connection.NoConnectionSheet
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private var loadingBar: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initializeLoadingProgress()
        homeViewModel.wordRepository = (requireActivity().application as BaseApplication).repository
        homeViewModel.isInternetAvailable = requireActivity().checkIfInternetAvailable()
        homeViewModel.initData()
        observeWords()
        showLoadingProgress()
        showNetworkSheet()
        return binding.root
    }

    private fun observeWords() {
        homeViewModel.observeWords.observe(viewLifecycleOwner, { wordsMap ->
            if (!wordsMap.isNullOrEmpty()) {
                homeViewModel.saveWordsInDb()
                setWordsList(wordsMap)
            }
        })
    }

    private fun setWordsList(wordsMap: HashMap<String, Int>) {
        rv_words_count.apply {
            adapter = HomeWordsCountAdapter(wordsMap)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun showLoadingProgress() {
        homeViewModel.showLoadingProcess.observe(viewLifecycleOwner, {
            if (it) showProgress()
            else hideProgress()
        })
    }

    private fun showProgress() {
        if (loadingBar != null && !requireActivity().isFinishing) loadingBar!!.show()
    }

    private fun hideProgress() {
        if (loadingBar != null && loadingBar!!.isShowing && !requireActivity().isFinishing) loadingBar!!.dismiss()
    }


    private fun showNetworkSheet() {
        homeViewModel.showNoConnection.observe(viewLifecycleOwner, {
            if (it) noConnection()
        })
    }

    private fun noConnection() {
        NoConnectionSheet().show(
            requireActivity().supportFragmentManager,
            Constants.NO_CONNECTION_SHEET
        )
    }

    private fun initializeLoadingProgress() {
        loadingBar = Dialog(requireContext(), R.style.Theme_AppCompat_DayNight)
        loadingBar!!.setCancelable(false)
        loadingBar!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val inflater = LayoutInflater.from(requireContext())
        val loadingView = inflater.inflate(R.layout.layout_loader, null)
        loadingBar!!.setContentView(loadingView)
        loadingBar!!.window!!.setBackgroundDrawable(
            ColorDrawable(Color.parseColor(getString(R.string.progressColor)))
        )
    }

}