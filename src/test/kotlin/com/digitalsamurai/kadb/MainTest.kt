package com.digitalsamurai.kadb

import com.digitalsamurai.kadb.client.AdbClient
import com.digitalsamurai.kadb.client.AdbClientBuilder
import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandProvider
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainTest  {
    private lateinit var test : AdbClient

    @BeforeEach
    internal fun before(){
        test  = AdbClientBuilder.Builder("C:\\Users\\andre\\AppData\\Local\\Android\\Sdk\\platform-tools\\")
            .setAutoStartAdbServer(true)
            .setCommandProvider(TerminalCommandProvider())
            .build()
    }

    @Test
    internal fun main() = runBlocking {

        test.getDevices().forEach {
            test.installApkOnDevice(it,"C:\\Users\\andre\\Desktop\\.partner\\PartnerApp2\\app\\build\\outputs\\apk\\debug\\a.apk")
        }


    }
}