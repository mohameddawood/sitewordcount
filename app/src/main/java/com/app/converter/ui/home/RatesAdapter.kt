package com.app.converter.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.converter.R


class RatesAdapter(private val dataSet: HashMap<String, Double>) :
RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    var isItemClicked = MutableLiveData<ClickValues>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRateName: TextView = view.findViewById(R.id.tv_rate_name)
        val tvRateValue: TextView = view.findViewById(R.id.tv_rate_value)
        val rateItem: ConstraintLayout = view.findViewById(R.id.rateItem)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rate_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var i = 0
        for ((key, value) in dataSet.entries) {
            if (position === i) {
                break
            }
            viewHolder.tvRateName.text = key
            viewHolder.tvRateValue.text = value.toString()
            i++
        }
        viewHolder.rateItem.setOnClickListener {
            isItemClicked.value = ClickValues(viewHolder.tvRateName.text.toString(), viewHolder.tvRateValue.text.toString().toDouble())
        }

    }

    override fun getItemCount() = dataSet.size

    class ClickValues(val rate:String,val value:Double)

}

