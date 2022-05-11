package com.digitalsamurai.kadb.client

import com.digitalsamurai.kadb.client.provider.AdbCommandProvider
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class AdbClient constructor(
    private val adbPath : String,
    private val provider: AdbCommandProvider) : AdbCommandProvider by provider{
init {

}


    suspend fun startAdbServer() : Boolean{
        return false
        // TODO: 06.05.2022  
    }
    suspend fun stopAdbServer() : Boolean{
        return false
        // TODO: 06.05.2022  
    }
}