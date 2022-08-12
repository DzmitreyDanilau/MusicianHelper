package com.musicianhelper.registration.impl.di

import com.musicianhelper.core.common.di.CommonProvider
import com.musicianhelper.core.common.di.CoroutineScopeModule
import com.musicianhelper.core.common.di.DispatchersModule
import com.musicianhelper.core.common.di.ResourceProviderModule
import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.data.api.UserDataSourceProvider
import com.musicianhelper.registration.impl.RegistrationViewModel
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
