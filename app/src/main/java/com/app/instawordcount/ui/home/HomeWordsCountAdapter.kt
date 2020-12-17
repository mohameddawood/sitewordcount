package com.app.instawordcount.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.instawordcount.manager.db.Word


class HomeWordsCountAdapter(private val modelList: HashMap<String, Int>) :
    RecyclerView.Adapter<HomeWordsViewHolder>() {
    private var mKeys: Array<String> = arrayOf()

    init {
        mKeys = modelList.keys.toTypedArray()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWordsViewHolder {
        return HomeWordsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeWordsViewHolder, position: Int) {
        val word = mKeys[position]
        val count = modelList[mKeys[position]]?:0
        holder.bind(Word(word, count))
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

}