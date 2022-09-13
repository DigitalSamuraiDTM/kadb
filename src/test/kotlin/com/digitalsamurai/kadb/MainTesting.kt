package com.digitalsamurai.kadb

import com.digitalsamurai.kadb.client.AdbClient
import com.digitalsamurai.kadb.client.AdbClientBuilder
import com.digitalsamurai.kadb.client.provider.commands.shell.input.keyevents.AdbKeyCode
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainTesting  {
    private lateinit var test : AdbClient

    @Before
    internal fun before(){
        test  = AdbClientBuilder.Builder("D:\\po\\sdk\\platform-tools\\")
            .setCommandProvider(AdbClientBuilder.CommandProvider.TERMINAL)
            .build()
    }

    @Test
    internal fun test() = runBlocking  {

        println(test.startAdbServer())
        val answer = test.getDevices()

//        answer.data.forEach {

//                println(test.Shell.screenshotOnDevice(it,"/sdcard/pyk.png").clearAnswer)

//        }
        answer.data.forEach {
            test.Shell.ActivityManager.startService(it,"com.digitalsamurai.kratorapp/.ForegoundServiceChecker")
            delay(10000)
            test.Shell.ActivityManager.stopService(it,"com.digitalsamurai.kratorapp/.ForegoundServiceChecker")

        }
        assert(true)

    }

    @After
    fun finish() = runBlocking{
        println(test.stopAdbServer())
    }
}