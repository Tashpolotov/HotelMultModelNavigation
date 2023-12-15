package com.example.navigation

import androidx.core.net.toUri

object AppRoutes {


    object FeatureFirst {
        val Entry = R.id.actionFeatureFirst
        object Deeplink {
            val FRAGMENT_FIRST = "example.feature://first".toUri()
        }
    }

    object FeatureSecond {
        val Entry = R.id.actionFeatureSecond
        object Deeplink {
            val FRAGMENT_SECOND = "example.feature://second".toUri()
        }

    }

    object FeatureThird {
        val Entry = R.id.actionFeaturesThird
        object Deeplink {
            val FRAGMENT_THIRD = "example.reservation://reservation".toUri()
        }

    }
}