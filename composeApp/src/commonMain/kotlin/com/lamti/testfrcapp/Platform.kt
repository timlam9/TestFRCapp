package com.lamti.testfrcapp

interface DataRepository {

    suspend fun getRemoteValue(key: String): String
}

expect fun getDataRepository(): DataRepository