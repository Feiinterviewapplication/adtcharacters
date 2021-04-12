package com.feiyatsu.networking.repository

import com.feiyatsu.networking.data.NetworkResource
import com.feiyatsu.networking.model.Character

interface CharactersRepositoryContract {
    suspend fun getCharacters(): NetworkResource<List<Character>>
}