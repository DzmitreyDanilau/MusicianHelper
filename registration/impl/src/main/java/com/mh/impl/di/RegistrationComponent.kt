package com.mh.impl.di

import com.mh.impl.RegistrationViewModel
import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.data.api.UserDataSourceProvider
import com.musicianhelper.di.CommonProvider
import com.musicianhelper.di.CoroutineScopeModule
import com.musicianhelper.di.DispatchersModule
import com.musicianhelper.di.ResourceProviderModule
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@Component(
  modules = [
    DispatchersModule::class,
    CoroutineScopeModule::class,
    ResourceProviderModule::class,
    UseCaseModule::class,
    RepositoryModule::class
  ],
  dependencies = [
    AuthenticationServiceProvider::class,
    CommonProvider::class,
    UserDataSourceProvider::class
  ]
)
@RegistrationScope
interface RegistrationComponent {
  val viewModel: RegistrationViewModel
}
