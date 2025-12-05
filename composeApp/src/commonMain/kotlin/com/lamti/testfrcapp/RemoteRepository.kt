package com.lamti.testfrcapp

object RemoteRepository {

    private val repository = getDataRepository()

    suspend fun data(): String {
        return "${repository.getRemoteValue("testTitle")}, ${repository.getRemoteValue("testDescription")}, ${repository.getRemoteValue("testString")}"
    }
}
