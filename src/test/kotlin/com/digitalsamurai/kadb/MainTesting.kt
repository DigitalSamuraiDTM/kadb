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
        test  = AdbClientBuilder.Builder("C:\\Users\\andre\\AppData\\Local\\Android\\Sdk\\platform-tools\\")
            .setCommandProvider(AdbClientBuilder.CommandProvider.TERMINAL)
            .build()
    }

    @Test
    internal fun test() = runBlocking  {

        println(test.startAdbServer())
        val answer = test.getDevices()


//
        println(test.stopAdbServer())

        assert(true)

    }

    @After
    fun finish() = runBlocking{
        println(test.stopAdbServer())
    }
}