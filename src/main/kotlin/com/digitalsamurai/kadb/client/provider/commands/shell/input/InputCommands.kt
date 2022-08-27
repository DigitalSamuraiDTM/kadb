package com.digitalsamurai.kadb.client.provider.commands.shell.input

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.input.keyevents.AdbKeyCode
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse
import java.awt.Point

interface InputCommands {
    //adb shell input keyevent "num_keyevent"
    suspend fun keyEvent(device : Device, keyEvent : AdbKeyCode) : RequestResponse<Boolean>
    suspend fun keyEvent(serial : String, keyEvent: AdbKeyCode) : RequestResponse<Boolean>

    //adb shell input text "text"
    suspend fun text(device : Device, text : String) : RequestResponse<Boolean>
    suspend fun text(serial : String, text : String) : RequestResponse<Boolean>

    //adb shell input tap "x" "y"
    suspend fun tap(device: Device, pointTap : Point) : RequestResponse<Boolean>
    suspend fun tap(serial: String, pointTap : Point) : RequestResponse<Boolean>

    //adb shell input swipe x1 y1 x2 y2 duration
    suspend fun swipe(device : Device, pointFrom: Point, pointTo:Point, duration:Int) : RequestResponse<Boolean>
    suspend fun swipe(serial : String, pointFrom:Point,pointTo:Point,duration:Int) : RequestResponse<Boolean>

    //It is not adb command, but I add it because it is easier for understand
    suspend fun longTap(device: Device, pointTap : Point,duration : Int) : RequestResponse<Boolean>
    suspend fun longTap(serial: String, pointTap : Point, duration : Int) : RequestResponse<Boolean>
}