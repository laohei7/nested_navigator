package com.laohei.nested_navigator.app

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Splash : Route()

    @Serializable
    class MainGraph : Route() {
        @Serializable
        data object Home : Route()

        @Serializable
        data object Subscription : Route()

        @Serializable
        data object Profile : Route()
    }

    @Serializable
    class Settings : Route() {
        @Serializable
        data object MainSetting : Route()

        @Serializable
        data object VideoSetting : Route()

        @Serializable
        data object OtherSetting : Route()
    }
}