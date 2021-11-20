package com.musicianhelper.di

import dagger.Component

@Component(
  dependencies = [
    CommonComponent::class
  ]
)
interface AppComponent : AppProvider

