plugins {
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

dependencies {
    implementation(Version.PAGING.PAGING_COMMON)
    implementation(Version.HILT.DAGGER_HILT_JAVAX)
}