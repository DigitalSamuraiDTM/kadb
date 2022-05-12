package com.digitalsamurai.kadb.client

import com.digitalsamurai.kadb.client.provider.commands.AdbCommandProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

class AdbClient constructor(
    private val adbPath : String,
    private val provider: AdbCommandProvider
) : AdbCommandProvider by provider{
init {

}


    suspend fun startAdbServer() : Boolean{
        val builder = ProcessBuilder(adbPath,"start-server")
        val process = withContext(Dispatchers.IO) { builder.start() }
        val out = BufferedReader(InputStreamReader(process.inputStream)).lines().toList()
        return out.isEmpty()
    }
    suspend fun stopAdbServer() : Boolean{
        val builder = ProcessBuilder(adbPath,"kill-server")
        val process = withContext(Dispatchers.IO) { builder.start() }
        val out = BufferedReader(InputStreamReader(process.inputStream)).lines().toList()
        return out.isEmpty()
    }
}