package com.app.converter.manager.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.converter.R
import com.app.converter.manager.connection.Resource
import com.app.converter.manager.utilities.Constants
import com.app.converter.ui.resource_layout.error.ErrorSheet
import com.app.converter.ui.resource_layout.no_connection.NoConnectionSheet
import com.app.converter.ui.resource_layout.success.SuccessSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity() {

    protected var loadingBar: Dialog? = null
    val baseViewModel: BaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeProgress()
        observeResponse()
    }


    open fun observeResponse() {
        baseViewModel.responseManager.getResponseManager().observe(this,
            Observer<Any> { responseResource ->
                responseResource as Resource<*>
                try {
                    when (responseResource.resourceStatus) {
                        Resource.ResourceStatus.LOADING -> {
                            showProgress()
                        }
                        Resource.ResourceStatus.AUTHENTICATED, Resource.ResourceStatus.HIDE_LOADING -> {
                            hideProgress()
                        }
                        Resource.ResourceStatus.SUCCESS -> {
                            hideProgress()
                            successMessage("error")
                        }
                        Resource.ResourceStatus.FAILED -> {
                            hideProgress()
                            failedMessage("error")
                        }
                        Resource.ResourceStatus.NO_CONNECTION -> {
                            hideProgress()
                            noConnection()
                        }
                        Resource.ResourceStatus.LOGOUT -> {
                        }
                    }
                } catch (ex: NullPointerException) {
                }
            })
    }

    //Snack bar :
    private fun successMessage(message: String?) {
        val successSheet = SuccessSheet()
        val bundle = Bundle()
        bundle.putString(Constants.MESSAGE, message)
        successSheet.arguments = bundle

        successSheet.show(supportFragmentManager, Constants.SUCCESS_SHEET)
    }

    private fun failedMessage(message: String?) {
        val errorSheet = ErrorSheet()
        val bundle = Bundle()
        bundle.putString(Constants.MESSAGE, message)
        errorSheet.arguments = bundle

        errorSheet.show(supportFragmentManager, Constants.ERROR_SHEET)
    }

    private fun noConnection() {
        NoConnectionSheet().show(supportFragmentManager, Constants.NO_CONNECTION_SHEET)
    }


    private fun initializeProgress() {
        loadingBar = Dialog(this, R.style.Theme_AppCompat_DayNight)
        loadingBar!!.setCancelable(false)
        loadingBar!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val inflater = LayoutInflater.from(this)
        val loadingView = inflater.inflate(R.layout.layout_loader, null)
        loadingBar!!.setContentView(loadingView)
        loadingBar!!.window!!.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#0effffff"))
        )
    }

    private fun showProgress() {
        if (loadingBar != null && !this.isFinishing) loadingBar!!.show()
    }

    private fun hideProgress() {
        if (loadingBar != null && loadingBar!!.isShowing && !this.isFinishing) loadingBar!!.dismiss()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}