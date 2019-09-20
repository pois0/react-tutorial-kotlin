rootProject.name = "react-tutorial-kotlin"

pluginManagement {
    repositories {
        mavenCentral()
        jcenter()
        maven(url="https://dl.bintray.com/kotlin/kotlin-eap")
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin2js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }

            if (requested.id.id == "org.jetbrains.kotlin.frontend") {
                useModule("org.jetbrains.kotlin:kotlin-frontend-plugin:${requested.version}")
            }
        }
    }
}
