plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.hotelmultmodelnavigation"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hotelmultmodelnavigation"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(project(":hotel:hotel_data"))
    implementation(project(":hotel:hotel_domain"))
    implementation(project(":hotel:hotel_presenter"))

    implementation(project(":room:room_data"))
    implementation(project(":room:room_domain"))
    implementation(project(":room:room_presenter"))


    implementation(project(":reservation:reservation_data"))
    implementation(project(":reservation:reservation_domain"))
    implementation(project(":reservation:reservation_presenter"))

    implementation(project(":core:navigation"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //hilt
    implementation(DaggerHilt.android)
    kapt(DaggerHilt.compiler)

    //retrofit
    implementation(Retrofit.retrofit)
    implementation (Retrofit.json)

    //glide
    implementation (Glide.glide)
    implementation (AnnotationProcessor.annotationProcessor)
    val nav_version = "2.7.6"
    //navigation
    implementation (Navigation.navigationFragment)
    implementation (Navigation.navigationKtx)
    api("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    //dotsIndic
    implementation (DotsIndicator.dotsindicator)
    implementation (DotsIndicator.circleindicator)

    //vp2
    implementation (Viewpager2.viewpager2)

    //okkhtp
    implementation (Okhttp.interceptor)
    implementation (Okhttp.okhttp)

    //Coroutines
    implementation (Coroutines.coroutines)

    //Viewbindingpropertydelegate
    implementation (Viewbindingpropertydelegate.viewbindingpropertydelegate)

    implementation (LottieAnimations.lottieAnimations)
}