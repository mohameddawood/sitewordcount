package com.app.instawordcount.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.instawordcount.manager.db.Word


class HomeWordsCountAdapter(private val modelList: HashMap<String, Int>) :
    RecyclerView.Adapter<HomeWordsViewHolder>() {
    private var wordsKeys: Array<String> = arrayOf()

    init {
        wordsKeys = modelList.keys.toTypedArray()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeWordsViewHolder.from(parent)


    override fun onBindViewHolder(holder: HomeWordsViewHolder, position: Int) {
        val word = wordsKeys[position]
        val count = modelList[wordsKeys[position]] ?: 0
        holder.bind(Word(word, count))
    }

    override fun getItemCount() = modelList.size
}