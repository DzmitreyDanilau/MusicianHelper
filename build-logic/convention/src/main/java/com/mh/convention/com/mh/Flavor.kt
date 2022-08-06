package com.mh.convention.com.mh

import com.android.build.api.dsl.CommonExtension
import com.mh.convention.com.mh.FlavorDimension.contentType
import org.gradle.api.Project

enum class FlavorDimension {
    contentType
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
enum class Flavor (val dimension : FlavorDimension, val applicationIdSuffix : String? = null) {
    demo(contentType, ".demo"),
    prod(contentType)
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        flavorDimensions += contentType.name
        productFlavors {
            Flavor.values().forEach{
                create(it.name) {
                    dimension = it.dimension.name
                }
            }
        }
    }
}