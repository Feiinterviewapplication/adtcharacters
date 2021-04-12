package com.feiyatsu.rickmortyapplication.ui.feed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feiyatsu.networking.model.Character
import com.feiyatsu.rickmortyapplication.R

class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView by lazy {
        view.findViewById<ImageView>(R.id.characterImage)
    }
    private val name: TextView by lazy {
        view.findViewById<TextView>(R.id.characterName)
    }
    private val status: TextView by lazy {
        view.findViewById<TextView>(R.id.characterStatus)
    }
    private val species: TextView by lazy {
        view.findViewById<TextView>(R.id.characterSpecies)
    }

    fun bindCharacterToView(character: Character) {
        name.text = character.name
        status.text = character.status
        species.text = character.species
        setImage(character)
    }

    fun setImage(character: Character) {
        if (character.image.isNotEmpty()) {
            Glide.with(itemView)
                .load(character.image)
                .centerCrop()
                .error(R.drawable.ic_empty)
                .fallback(R.drawable.ic_empty)
                .into(image)
        }
    }
}