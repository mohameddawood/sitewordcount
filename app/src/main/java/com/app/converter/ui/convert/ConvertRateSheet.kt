package com.app.converter.ui.convert

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.app.converter.R
import com.app.converter.databinding.ConverteSheetBinding
import com.app.converter.model.entities.ConvertRateRequest
import com.app.converter.ui.convert.ConvertRatesSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConvertRateSheet(val rate: String,val to:String,val value: Double) : BottomSheetDialogFragment() {

    private lateinit var convertSheetBinding: ConverteSheetBinding
    private val convertRatesSheetViewModel: ConvertRatesSheetViewModel by viewModel()
    val request: ConvertRateRequest by inject()
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        convertSheetBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.converte_sheet,
            container,
            false
        )
        return convertSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        convertSheetBinding.lifecycleOwner = this
        convertSheetBinding.viewModel = convertRatesSheetViewModel
        request.from = rate
        request.to = to
        convertSheetBinding.tvNewRateValue.text = value.toString()
        convertSheetBinding.tvNewRateName.text = to
        convertSheetBinding.request = request
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeSuccess()
        observeError()
        observeCloseClick()
    }

    private fun observeError() {
        convertRatesSheetViewModel.observeError.removeObservers(viewLifecycleOwner)
        convertRatesSheetViewModel.observeError.observe(viewLifecycleOwner, Observer<String?> { msg ->
            Toast.makeText(
                activity,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun observeSuccess() {
        convertRatesSheetViewModel.observSucces.removeObservers(viewLifecycleOwner)
        convertRatesSheetViewModel.observSucces.observe(viewLifecycleOwner, Observer<Boolean?> {
            try {
                bottomSheetBehavior!!.isHideable = true
                Toast.makeText(requireContext(), "Success ....but api key limited :D ^_^", Toast.LENGTH_SHORT).show()
            } catch (ignored: NullPointerException) {
            }
        })
    }

    private fun observeCloseClick() {
        convertRatesSheetViewModel.closeClick!!.removeObservers(viewLifecycleOwner)
        convertRatesSheetViewModel.closeClick!!.observe(viewLifecycleOwner, Observer {
            try {
                bottomSheetBehavior!!.isHideable = true
                bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN
            } catch (ignored: NullPointerException) {
            }
        })
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        var bottomSheet: View? = null
        if (dialog != null) {
            bottomSheet = dialog.findViewById(R.id.design_bottom_sheet)
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
        val view = view
        val finalBottomSheet = bottomSheet
        view!!.post {
            val parent = view.parent as View
            val params = parent.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior
            bottomSheetBehavior = behavior as BottomSheetBehavior<*>?
            bottomSheetBehavior!!.peekHeight = view.measuredHeight
            (finalBottomSheet!!.parent as View).setBackgroundColor(Color.TRANSPARENT)
        }
    }

}