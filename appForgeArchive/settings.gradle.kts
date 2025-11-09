pluginManagement {
	repositories {
		maven {
			name = "GTNH Maven"
			url = uri("https://nexus.gtnewhorizons.com/repository/public/")
			mavenContent {
				includeGroup("com.gtnewhorizons")
				includeGroup("com.gtnewhorizons.retrofuturagradle")
			}
		}
		maven("https://maven.architectury.dev/")
		maven("https://maven.fabricmc.net")
		maven("https://maven.minecraftforge.net/")
		maven("https://repo.essential.gg/repository/maven-public/")
		maven("https://repo.essential.gg/repository/maven-releases/")
		maven("https://repo.sleeping.town")
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
	}
	resolutionStrategy {
		eachPlugin {
			when (requested.id.id) {
				"gg.essential.loom" -> useModule("gg.essential:architectury-loom:${requested.version}")
			}
		}
	}
}

dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
	repositories {
		maven {
			name = "GTNH Maven"
			url = uri("https://nexus.gtnewhorizons.com/repository/public/")
			mavenContent {
				includeGroup("com.gtnewhorizons")
				includeGroup("com.gtnewhorizons.retrofuturagradle")
			}
		}
		maven("https://maven.architectury.dev/")
		maven("https://maven.fabricmc.net")
		maven("https://maven.minecraftforge.net/")
		maven("https://repo.essential.gg/repository/maven-public/")
		maven("https://repo.essential.gg/repository/maven-releases/")
		maven("https://repo.sleeping.town")
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "latest.release"
}

include(":1.7.10")
