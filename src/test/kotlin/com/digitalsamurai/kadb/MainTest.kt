package com.digitalsamurai.kadb

import com.digitalsamurai.kadb.client.AdbClient
import com.digitalsamurai.kadb.client.AdbClientBuilder
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
//        with(test.getDevices()) {
//            println(this.clearAnswer)
//           this.data.forEach {
//                val a = test.installApkOnDevice(it, "D:\\.WORK_PROJECTS\\.partner\\PartnerApp2\\app\\build\\outputs\\apk\\debug\\a.apk")
//               println(a.requestStatus.toString()+" "+a.data.toString())
//                val b =test.Shell.ActivityManager.startApplicationOnDevice(it,"com.apponewin.partnerapp/com.apponewin.partnerapp.MainActivity")
//               println(b.clearAnswer)
//            }
//
//
//        }

        val answer = test.pullFileFromDevice("3fb71e7a","/sdcard/window_dump.xml")
        println(answer.clearAnswer)
        println("${answer.data?.first} : ${answer.data?.second}")

        println(test.stopAdbServer())

    }
}