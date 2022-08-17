package com.digitalsamurai.kadb

import com.digitalsamurai.kadb.client.AdbClient
import com.digitalsamurai.kadb.client.AdbClientBuilder
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandExecutor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
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

        answer.data.forEach {
            println(test.Shell.Dumpsys.Battery.getInfoBatteryOnDevice(it))
            delay(2000)
            println(test.Shell.Dumpsys.Battery.setBatteryLevel(it,50))
            delay(2000)
            println(test.Shell.Dumpsys.Battery.resetBatteryOnDevice(it))

        }

        assert(true)

    }

    @After
    fun finish() = runBlocking{
        println(test.stopAdbServer())
    }
}