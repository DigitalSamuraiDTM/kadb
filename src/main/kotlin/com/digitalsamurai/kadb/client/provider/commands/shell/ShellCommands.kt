package com.digitalsamurai.kadb.client.provider.commands.shell

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.activitymanager.ActivityManagerCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.DumpsysCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.input.InputCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.uiautomator.UiautomatorCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface ShellCommands {
    val UiAutomator : UiautomatorCommands
    val ActivityManager : ActivityManagerCommands
    val Dumpsys : DumpsysCommands
    val Input : InputCommands
    //adb shell screencap -p '"/path/to/save/screen.png"'
    suspend fun screenshotOnDevice(device : Device, screenshotDevicePath : String) : RequestResponse<Boolean>
    suspend fun screenshotOnDevice(serial : String, screenshotDevicePath : String) : RequestResponse<Boolean>



}