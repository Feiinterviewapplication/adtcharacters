package com.feiyatsu.rickmortyapplication.repo

import com.feiyatsu.networking.api.CharactersApi
import com.feiyatsu.networking.data.NetworkResource
import com.feiyatsu.networking.model.Character
import com.feiyatsu.networking.model.CharacterResponse
import com.feiyatsu.networking.repository.CharactersRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepository(
    private val charactersApi: CharactersApi
) : CharactersRepositoryContract {

    private var currentPage: Int = 1
    private var isLoadingPossible = true
    private val characterCache: MutableList<Character> = mutableListOf()

    override suspend fun getCharacters(): NetworkResource<List<Character>> {
        if (!isLoadingPossible) NetworkResource.Error<List<Character>>(Exception("Reached the end of the list"))
        return withContext(Dispatchers.IO) {
            try {
                val response = charactersApi.getCharacters(currentPage)
                handleCharacterResponse(response)
                NetworkResource.Success(characterCache)
            } catch (e: Throwable) {
                e.printStackTrace()
                NetworkResource.Error(e)
            }
        }
    }

    private fun handleCharacterResponse(response: CharacterResponse) {
        characterCache.addAll(response.characters)
        if (response.characterInfo.nextUrl.isNullOrBlank()
                .not() && response.characters.isNotEmpty()
        ) {
            currentPage += 1
        } else {
            isLoadingPossible = false
        }
    }
}
