pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HotelMultModelNavigation"
include(":app")
include(":room:room_data")
include(":room:room_domain")
include(":room:room_presenter")
include(":hotel:hotel_data")
include(":hotel:hotel_domain")
include(":hotel:hotel_presenter")
include(":reservation:reservation_data")
include(":reservation:reservation_domain")
include(":reservation:reservation_presenter")
include(":core:navigation")
