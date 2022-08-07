package com.musicianhelper.login.impl.di

import com.musicianhelper.core.common.di.CommonProvider
import com.musicianhelper.core.common.di.DispatchersModule
import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.login.impl.ui.LoginViewModel
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@Component(
  modules = [UseCaseModule::class, RepositoryModule::class, DispatchersModule::class],
  dependencies = [CommonProvider::class, AuthenticationServiceProvider::class]
)
interface LoginComponent {
  val viewModel: LoginViewModel
}
