package com.lamti.testfrcapp

import android.os.Build
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlin.time.Duration.Companion.seconds

class AndroidDataRepository : DataRepository {

    private val name: String = Build.VERSION.SDK_INT.toString()

    override suspend fun getRemoteValue(key: String): String {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.ensureInitialized()

        remoteConfig.settings {
            this.minimumFetchInterval = 30.seconds
        }

        val success = remoteConfig.fetchAndActivate()
        val jsonString = remoteConfig.getValue(key = key).asString()

        return "$jsonString| Android: $name"
    }
}

actual fun getDataRepository(): DataRepository = AndroidDataRepository()
