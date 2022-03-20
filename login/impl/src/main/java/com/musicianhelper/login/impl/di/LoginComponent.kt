package com.musicianhelper.login.impl.di

import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.di.CommonProvider
import com.musicianhelper.di.DispatchersModule
import com.musicianhelper.login.impl.login.di.RepositoryModule
import com.musicianhelper.login.impl.login.di.UseCaseModule
import com.musicianhelper.login.impl.login.ui.LoginViewModel
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@LoginScope
@Component(
  modules = [UseCaseModule::class, RepositoryModule::class, DispatchersModule::class],
  dependencies = [CommonProvider::class, AuthenticationServiceProvider::class]
)
interface LoginComponent {
  val viewModel: LoginViewModel
}
