package com.app.instawordcount.ui.resource_layout.no_connection

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import com.app.instawordcount.R
import com.app.instawordcount.databinding.LayoutNoConnectionBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoConnectionSheet : BottomSheetDialogFragment() {
    private lateinit var noConnectionBinding: LayoutNoConnectionBinding
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        noConnectionBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_no_connection, container, false)
        noConnectionBinding.lifecycleOwner = this
        noConnectionBinding.clickListener = this

        return noConnectionBinding.root
    }

    fun onCloseClicked() {
        try {
            bottomSheetBehavior!!.isHideable = true
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN
        } catch (ignored: NullPointerException) {
        }
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
            val params =
                parent.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior
            bottomSheetBehavior = behavior as BottomSheetBehavior<*>?
            bottomSheetBehavior!!.peekHeight = view.measuredHeight
            (finalBottomSheet!!.parent as View).setBackgroundColor(Color.TRANSPARENT)
        }
    }
}

