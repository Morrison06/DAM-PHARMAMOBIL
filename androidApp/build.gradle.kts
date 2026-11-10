import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.uiToolingPreview)
    debugImplementation(libs.compose.uiTooling)
}

// Las credenciales están FUERA de Git. La compilación debug no las necesita.
val signingPropertiesFile = rootProject.file("keystore.properties")
val signingProperties = Properties().apply {
    if (signingPropertiesFile.isFile) {
        signingPropertiesFile.inputStream().use { load(it) }
    }
}
val hasReleaseCredentials = listOf(
    "storeFile", "storePassword", "keyAlias", "keyPassword"
).all { !signingProperties.getProperty(it).isNullOrBlank() }

android {
    namespace = "pe.edu.upeu.pharmamobil"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "pe.edu.upeu.pharmamobil"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
    }

    if (hasReleaseCredentials) {
        signingConfigs {
            create("release") {
                val suppliedStoreFile = requireNotNull(signingProperties.getProperty("storeFile"))
                storeFile = rootProject.file(suppliedStoreFile)
                storePassword = signingProperties.getProperty("storePassword")
                keyAlias = signingProperties.getProperty("keyAlias")
                keyPassword = signingProperties.getProperty("keyPassword")
            }
        }
    }

    buildTypes {
        getByName("release") {
            // Primero probar el APK release para descartar problemas de R8.
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            if (hasReleaseCredentials) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures { compose = true }
}

// No permitimos generar accidentalmente un "release" sin la firma personal.
// Los builds de desarrollo y las pruebas siguen funcionando sin secretos.
gradle.taskGraph.whenReady {
    if (!hasReleaseCredentials && allTasks.any { task ->
            task.project == project && (
                task.name == "assembleRelease" ||
                task.name == "bundleRelease" ||
                task.name == "packageRelease" ||
                task.name == "signReleaseBundle" ||
                task.name == "validateSigningRelease"
            )
        }) {
        throw GradleException(
            "Falta keystore.properties válido. Copia keystore.properties.example " +
                "y configura un almacén .jks privado antes de generar release."
        )
    }
}
