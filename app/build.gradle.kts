plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.example.stockapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.stockapp"
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // OpenCSV
    implementation ("com.opencsv:opencsv:5.5.2")

    // Coil
    implementation ("io.coil-kt:coil-compose:2.5.0")

    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    ksp ("com.google.dagger:hilt-android-compiler:2.50")
    ksp ("androidx.hilt:hilt-compiler:1.1.0")
    ksp ("com.google.dagger:dagger-compiler:2.50")
    ksp ("com.google.dagger:hilt-compiler:2.50")
    testImplementation("com.google.dagger:hilt-android-testing:2.50")
    kspTest("com.google.dagger:hilt-android-compiler:2.50")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.50")
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.50")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Room
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    ksp ("androidx.room:room-compiler:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // Compose Destination
    implementation ("io.github.raamcosta.compose-destinations:core:1.8.42-beta")
    ksp ("io.github.raamcosta.compose-destinations:ksp:1.8.42-beta")

    // Compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation ("androidx.compose.material:material-icons-extended:$1.6.1")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.17.0")
    implementation ("androidx.paging:paging-compose:3.2.1")
    implementation ("androidx.activity:activity-compose:1.8.2")
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // mockito
    testImplementation ("org.mockito:mockito-core:4.11.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")


    // corrotinas
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0-RC2")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0-RC2")

    // google truth
    testImplementation ("com.google.truth:truth:1.4.1")
    androidTestImplementation ("com.google.truth:truth:1.4.1")

    // expresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // mock web server
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
}