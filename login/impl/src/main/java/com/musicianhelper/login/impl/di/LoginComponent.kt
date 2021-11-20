package com.musicianhelper.login.impl.di

import dagger.Component

@LoginScope
@Component(
  modules = [LoginModule::class],
  dependencies = []
)
interface LoginComponent {
}
