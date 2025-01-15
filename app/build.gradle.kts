plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")}

android {
    namespace = "com.nr.cvsflickrcodechallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nr.cvsflickrcodechallenge"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Third Party Libraries
    implementation(libs.coil.compose)
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.dagger:hilt-android:2.45")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt ("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0") //Logging network requests

    // Testing Libraries
    // JUnit for testing
    testImplementation ("junit:junit:4.13.2")

    // Mockito for mocking
    testImplementation ("org.mockito:mockito-core:4.8.0")
    testImplementation ("org.mockito:mockito-inline:4.8.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")
    // Coroutine testing
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    // LiveData testing
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
}

kapt {
    correctErrorTypes = true
}

