package com.feiyatsu.rickmortyapplication.di

import com.feiyatsu.networking.CharactersNetworkLibrary
import com.feiyatsu.networking.api.CharactersApi
import com.feiyatsu.networking.repository.CharactersRepositoryContract
import com.feiyatsu.rickmortyapplication.repo.CharactersRepository
import com.feiyatsu.rickmortyapplication.repo.FakeCharacterRepository
import org.koin.dsl.module

val rickAppModule = module {
    single { CharactersNetworkLibrary() }
    single { get<CharactersNetworkLibrary>().charactersApi }

    // NOTE! The api is down right now (checked on browser and phone)
    // Use the FakeCharacterRepository when you want to view the app if the api is down
    single<CharactersRepositoryContract> { CharactersRepository(get()) }
}