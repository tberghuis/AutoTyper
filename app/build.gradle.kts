plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.jetbrains.kotlin.serialization)
  alias(libs.plugins.ksp)
}

android {
  namespace = "dev.tberghuis.btmacrokb"
  compileSdk = 35

  defaultConfig {
    applicationId = "dev.tberghuis.btmacrokb"
    // todo test sdk 28 29
    minSdk = 28
    targetSdk = 34
    versionCode = 3
    versionName = "1.2"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
      // fix ktor server failed build
//      pickFirsts.add("META-INF/*")
      excludes.add("META-INF/INDEX.LIST")
      excludes.add("META-INF/io.netty.versions.properties")
    }
  }
}

kotlin {
  jvmToolchain(17)
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.accompanist.permissions)
  implementation(libs.androidx.lifecycle.viewModelCompose)
  implementation(libs.room.runtime)
  implementation(libs.room.ktx)
  // find icon name https://fonts.google.com/icons
  implementation(libs.androidx.compose.material.iconsExtended)
  // need for @Serializable to compile when ktor not included
  // https://stackoverflow.com/questions/74366590/intellij-ktor-kotlinx-serialization-cannot-access-serializable-it-is-inter
  implementation(libs.kotlinx.serialization.json)

  // glance not used in prod yet, only in tmp code
  implementation(libs.androidx.glance.appwidget)
  implementation(libs.androidx.glance.material3)

  testImplementation(libs.junit)

  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.ui.test.junit4)

  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)

  ksp(libs.room.compiler)


  // for dev only
//  val ktorVersion = "2.3.12"
//  implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
//  implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
//  implementation("io.ktor:ktor-server-config-yaml:$ktorVersion")
}

ksp {
  arg("room.schemaLocation", "$projectDir/schemas")
}