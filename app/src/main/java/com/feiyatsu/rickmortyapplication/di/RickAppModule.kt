package com.feiyatsu.rickmortyapplication.di

import com.feiyatsu.networking.CharactersNetworkLibrary
import com.feiyatsu.networking.repository.CharactersRepositoryContract
import com.feiyatsu.rickmortyapplication.repo.CharactersRepository
import org.koin.dsl.module

val rickAppModule = module {
    single { CharactersNetworkLibrary() }
    single { get<CharactersNetworkLibrary>().charactersApi }


    /**
     *  NOTE! The api is down every once in a while ( I think that's intended?)
     *  Use the FakeCharacterRepository when you want to view the app if the api is down
     *  by declaring it on line 18 { FakeCharacterRepository() }
     */
    single<CharactersRepositoryContract> { CharactersRepository(get()) }
}