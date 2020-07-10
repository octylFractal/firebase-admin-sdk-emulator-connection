plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.firebase:firebase-admin:6.14.0")
}

application {
    mainClassName = "net.octyl.firebase.emulator.App"
}

tasks.named<Test>("test") {
    // Use junit platform for unit tests
    useJUnitPlatform()
}
