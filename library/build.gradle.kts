plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "androidx.kylin.luban"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}

afterEvaluate {
    publishing {
        publications.create("release", MavenPublication::class.java) {
            from(components["release"])
            groupId = "com.github.androidx-kylin"
            artifactId = "luban-ktx"
            version = "1.0.0"
            pom {
                name = "android-compass"
                url = "https://github.com/raedev/android-compass"
                developers {
                    developer {
                        id = "RAE"
                        name = "RAE"
                        email = "raedev666@gmail.com"
                    }
                }
            }
        }
    }

}