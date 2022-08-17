package com.digitalsamurai.kadb

import com.digitalsamurai.kadb.client.AdbClient
import com.digitalsamurai.kadb.client.AdbClientBuilder
import kotlinx.coroutines.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class MainTest  {
    private lateinit var test : AdbClient

    @BeforeEach
    internal fun before(){
        test  = AdbClientBuilder.Builder("D:\\po\\sdk\\platform-tools\\")
            .setCommandProvider(AdbClientBuilder.CommandProvider.TERMINAL)
            .build()
    }

    @Test
    internal fun main() = runBlocking {

        println(test.startAdbServer())
        val answer = test.getDevices()

        println(answer)







        println(test.stopAdbServer())

    }
}