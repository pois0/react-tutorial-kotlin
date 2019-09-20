import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

val kotlinxHtmlVersion = "0.6.12"
val reactVersion = "16.9.0"
val reactWrapperVersion = "16.9.0-pre.82-kotlin-1.3.41"
val ktorVersion = "1.2.2"

buildscript {
    val kotlinVersion by extra("1.3.50")
    val kotlinFrontendVersion by extra("0.0.45")

    repositories {
        mavenCentral()
        jcenter()
        maven(url="https://dl.bintray.com/kotlin/kotlin-eap")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:$kotlinFrontendVersion")
    }
}

plugins {
    id("kotlin2js") version "1.3.50"
    id("org.jetbrains.kotlin.frontend") version "0.0.45"
}

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("io.ktor:ktor-client-js:$ktorVersion")
    implementation("org.jetbrains:kotlin-react:$reactWrapperVersion")
    implementation("org.jetbrains:kotlin-react-dom:$reactWrapperVersion")
}

kotlinFrontend {
    downloadNodeJsVersion = "latest"

    npm {
        dependency("react")
        dependency("react-dom")
    }

    bundle<WebPackExtension>("webpack") {
        this as WebPackExtension
        bundleName = "tictactoe"
        contentPath = file("src/main/web")
    }
}

gradle.projectsEvaluated {
    tasks.withType(Kotlin2JsCompile::class) {
        kotlinOptions {
            moduleKind = "commonjs"
        }
    }
}
