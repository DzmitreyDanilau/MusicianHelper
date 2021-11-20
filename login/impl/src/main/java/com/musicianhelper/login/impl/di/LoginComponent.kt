package com.musicianhelper.login.impl.di

import com.musicianhelper.data.api.UserDataSourceProvider
import com.musicianhelper.login.impl.ui.LoginViewModel
import dagger.Component

@LoginScope
@Component(
  modules = [UseCaseModule::class, RepositoryModule::class],
  dependencies = [UserDataSourceProvider::class]
)
interface LoginComponent {

  val viewModel: LoginViewModel
}
