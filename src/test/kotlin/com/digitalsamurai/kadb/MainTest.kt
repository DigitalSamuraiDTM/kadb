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
//        CoroutineScope(Dispatchers.IO).launch {
//            println(answer.clearAnswer)
//            val q = test.sendCommand("logcat")
//            println(q.data)
//        }
        val a = test.installApkOnDevice(answer.data[0], "D:\\.WORK_PROJECTS\\.partner\\PartnerApp2\\app\\build\\outputs\\apk\\debug\\a.apk")
        println(a.requestStatus.toString()+" "+a.data.toString())
        val b =test.Shell.ActivityManager.startApplicationOnDevice(answer.data[0],"com.apponewin.partnerapp/com.apponewin.partnerapp.MainActivity")
        println(b.clearAnswer)
        delay(10*1000)

        val dump = test.Shell.UiAutomator.dump(answer.data[0])

        if(dump.data!=null){
            val pullAnswer = test.pullFileFromDevice("3fb71e7a",dump.data!!, "D:\\work")
            println(pullAnswer.clearAnswer)
            println("${pullAnswer.data?.first} : ${pullAnswer.data?.second}")

            val file = File("D:\\work\\window_dump.xml")

            println("FILE HASHCODE: ${file.readLines().hashCode()}")
            //1362201386
            //78308962 WEBVIEW
            //78308962 WEBVIEW
            //-1931483432 LOADING
            //-1931483432 LOADING
        }



        delay(5000)
        test.uninstallApkOnDevice(answer.data[0],"com.apponewin.partnerapp")






        println(test.stopAdbServer())

    }
}