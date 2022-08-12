package com.musicianhelper.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

typealias Destinations = Map<Class<out FeatureEntry>, @JvmSuppressWildcards FeatureEntry>

interface FeatureEntry {

  val featureRoute: String

  val arguments: List<NamedNavArgument>
    get() = emptyList()

  val deepLinks: List<NavDeepLink>
    get() = emptyList()
}

interface ComposableFeatureEntry : FeatureEntry {

  fun NavGraphBuilder.composable(navController: NavHostController, destinations: Destinations) {
    composable(featureRoute, arguments, deepLinks) { backStackEntry ->
      Composable(navController, destinations, backStackEntry)
    }
  }

  @Composable
  fun NavGraphBuilder.Composable(
    navController: NavHostController,
    destinations: Destinations,
    backStackEntry: NavBackStackEntry
  )
}

/**
 * It covers cases when a feature implements nested navigation and
 * contains its own navigation subgraph. It contains a navigation function
 * that would allow a feature entry to define a navigation subgraph in it.
 */
interface AggregateFeatureEntry : FeatureEntry {

  fun NavGraphBuilder.navigation(navController: NavHostController, destinations: Destinations)
}

inline fun <reified T : FeatureEntry> Destinations.find(): T =
  findOrNull() ?: error("Unable to find '${T::class.java}' destination.")

inline fun <reified T : FeatureEntry> Destinations.findOrNull(): T? =
  this[T::class.java] as? T
