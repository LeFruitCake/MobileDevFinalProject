plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.finalproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.finalproject"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:21.0.0")

    //Firebase real-time db dependency
    implementation("com.firebaseui:firebase-ui-database:8.0.2")

    //Glider to show image
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //Circle view for image
    implementation("de.hdodenhof:circleimageview:3.1.0")

    //Recycler view dependency
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    //Card view dependency
    implementation("androidx.cardview:cardview:1.0.0")

    //Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    //Firebase auth library
    implementation("com.google.firebase:firebase-auth")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
