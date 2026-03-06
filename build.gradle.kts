// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.4.1" apply false
    id("com.android.library") version "8.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
}

// Thay thế cho khối ext cũ
val kotlinVersion by extra("1.9.24")
val hiltVersion by extra("2.51")
val roomVersion by extra("2.6.1")
val navVersion by extra("2.7.7")
