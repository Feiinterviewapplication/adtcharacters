package com.feiyatsu.rickmortyapplication.di

import com.feiyatsu.rickmortyapplication.ui.feed.FeedViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { FeedViewModel(get()) }
}