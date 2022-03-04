package com.musicianhelper.login.impl.di

import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.login.impl.ui.LoginViewModel
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@LoginScope
@Component(
  modules = [UseCaseModule::class, RepositoryModule::class],
  dependencies = [AuthenticationServiceProvider::class]
)
interface LoginComponent {
  val viewModel: LoginViewModel
}
