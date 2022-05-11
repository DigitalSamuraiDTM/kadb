package com.digitalsamurai.kadb

import com.digitalsamurai.kadb.client.AdbClient
import com.digitalsamurai.kadb.client.AdbClientBuilder
import com.digitalsamurai.kadb.client.provider.NetworkCommandProvider
import com.digitalsamurai.kadb.client.provider.TerminalCommandProvider
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainTest  {
    private lateinit var test : AdbClient
    private var k : String = ""
    @BeforeEach
    internal fun before(){
        val test  = AdbClientBuilder.Builder()
            .setAdbPath("D:\\po\\sdk\\platform-tools\\adb.exe")
            .setAutoStartAdbServer(true)
            .setCommandProvider(TerminalCommandProvider())
            .build()
    }

    @Test
    internal fun main() = runBlocking {
        println(test.sendCommand("obema"))
    }
}