package com.app.instawordcount.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.instawordcount.databinding.ItemWrodsCountBinding
import com.app.instawordcount.manager.db.Word
import org.koin.core.KoinComponent

class HomeWordsViewHolder(private val binding: ItemWrodsCountBinding) :
    RecyclerView.ViewHolder(binding.root), KoinComponent {

    fun bind(word: Word) {
        binding.word = word
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): HomeWordsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemWrodsCountBinding.inflate(layoutInflater, parent, false)

            return HomeWordsViewHolder(binding)
        }


    }
}