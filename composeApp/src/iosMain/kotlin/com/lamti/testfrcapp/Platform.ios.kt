package com.lamti.testfrcapp

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.remoteConfig
import platform.UIKit.UIDevice
import kotlin.time.Duration.Companion.seconds

class IOSDataRepository: DataRepository {
    private val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    override suspend fun getRemoteValue(key: String): String {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.ensureInitialized()

        remoteConfig.settings {
            this.minimumFetchInterval = 30.seconds
        }

        val success = remoteConfig.fetchAndActivate()
        val jsonString = remoteConfig.getValue(key = key).asString()

        return "$jsonString | iOS: $name"
    }
}

actual fun getDataRepository(): DataRepository = IOSDataRepository()
