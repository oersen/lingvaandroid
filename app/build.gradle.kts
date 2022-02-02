plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    kotlin("android")
    kotlin("plugin.serialization")
}

// TODO: consolidate to buildSrc
val compose_version = "1.0.4"
val hilt_agp_version = "2.38.1"

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "dev.atajan.lingva_android"
        minSdk = 26
        targetSdk = 31
        versionCode = 10
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            postprocessing {
                isRemoveUnusedCode = true
                isRemoveUnusedResources = true
                isObfuscate = false
                isOptimizeCode = true
            }
        }
        debug {
//            postprocessing {
//                isRemoveUnusedCode = true
//                isRemoveUnusedResources = true
//                isObfuscate = false
//                isOptimizeCode = true
//            }
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
        kotlinCompilerExtensionVersion = compose_version
    }
    packagingOptions { // https://stackoverflow.com/a/47509465/8685398
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

kotlin.sourceSets.all {
    languageSettings.apply {
        optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
        optIn("androidx.compose.ui.ExperimentalComposeUiApi")
        optIn("androidx.compose.foundation.ExperimentalFoundationApi")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Result
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.12")

    // Ktor
    implementation("io.ktor:ktor-client-android:1.5.2")
    implementation("io.ktor:ktor-client-serialization:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("io.ktor:ktor-client-logging-jvm:1.5.2")

    // Compose
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.material3:material3:1.0.0-alpha01")

    // note that due to the very large size of this dependency you should make sure to use
    // R8 / ProGuard to remove unused icons from your application.
    implementation("androidx.compose.material:material-icons-extended:$compose_version")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt_agp_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_agp_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
}
