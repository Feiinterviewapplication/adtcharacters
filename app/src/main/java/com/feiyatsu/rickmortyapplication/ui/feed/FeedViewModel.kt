package com.feiyatsu.rickmortyapplication.ui.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feiyatsu.networking.data.NetworkResource
import com.feiyatsu.networking.model.Character
import com.feiyatsu.networking.repository.CharactersRepositoryContract
import kotlinx.coroutines.launch

class FeedViewModel(
    private val charactersRepo: CharactersRepositoryContract
) : ViewModel() {

    val characters = MutableLiveData<NetworkResource<List<Character>>>()

    fun fetchCharacters() {
        characters.value = NetworkResource.Loading()
        viewModelScope.launch {
            val response = charactersRepo.getCharacters()
            characters.postValue(response)
        }
    }
}