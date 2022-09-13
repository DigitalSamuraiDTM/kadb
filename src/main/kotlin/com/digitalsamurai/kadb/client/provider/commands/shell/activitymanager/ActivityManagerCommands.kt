package com.digitalsamurai.kadb.client.provider.commands.shell.activitymanager

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface ActivityManagerCommands {

    //adb shell am start -n com.package.name/com.package.name.ActivityName
    suspend fun startApplication(device: Device, application: String): RequestResponse<Boolean>
    suspend fun startApplication(serial: String, application: String): RequestResponse<Boolean>


    //adb shell am startservice com.package.name/.ServiceName
    suspend fun startService(device: Device, service : String) :RequestResponse<Boolean>
    suspend fun startService(serial: String, service : String) :RequestResponse<Boolean>

    //adb shell am stopservice com.package.name/.ServiceName
    suspend fun stopService(device: Device, service : String) :RequestResponse<Boolean>
    suspend fun stopService(serial: String, service : String) :RequestResponse<Boolean>

    //adb shell am start-foreground-service com.package.name/.ServiceName
    suspend fun startForegroundService(device: Device, service : String) :RequestResponse<Boolean>
    suspend fun startForegroundService(serial: String, service : String) :RequestResponse<Boolean>

    suspend fun killAll(device : Device) : RequestResponse<Boolean>
}