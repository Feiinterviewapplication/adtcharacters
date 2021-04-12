package com.feiyatsu.rickmortyapplication.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feiyatsu.networking.model.Character
import com.feiyatsu.rickmortyapplication.R

interface CharacterListener {
    fun onCharacterClicked(character: Character)
}

class CharacterAdapter(
    private val listener: CharacterListener
) : RecyclerView.Adapter<CharacterViewHolder>() {

    private val characters: MutableList<Character> = mutableListOf()

    fun addNewCharacters(items: List<Character>) {
        characters.clear()
        characters.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_character, parent, false)
    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindCharacterToView(characters[position])
        holder.itemView.setOnClickListener {
            listener.onCharacterClicked(characters[holder.adapterPosition])
        }
    }

    override fun getItemCount() = characters.size
}