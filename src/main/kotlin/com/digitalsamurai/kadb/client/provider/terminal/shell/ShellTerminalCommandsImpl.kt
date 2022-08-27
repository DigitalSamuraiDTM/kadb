package com.digitalsamurai.kadb.client.provider.terminal.shell

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.ShellCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.activitymanager.ActivityManagerCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.DumpsysCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.input.InputCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.uiautomator.UiautomatorCommands
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandExecutor
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

class ShellTerminalCommandsImpl(private val adbPath : String,
                                override val ActivityManager : ActivityManagerCommands,
                                override val UiAutomator: UiautomatorCommands,
                                override val Dumpsys: DumpsysCommands,
                                override val Input: InputCommands) : ShellCommands {

    override suspend fun screenshotOnDevice(device: Device, screenshotDevicePath: String): RequestResponse<Boolean> {
        //adb shell screencap -p '"/path/to/save/screen.png"'
        return screenshotOnDevice(device.serial,screenshotDevicePath)
    }

    override suspend fun screenshotOnDevice(serial: String, screenshotDevicePath: String): RequestResponse<Boolean> {
        val command = listOf(adbPath, "-s", serial, "shell", "screencap","-p", screenshotDevicePath)
        val response = TerminalCommandExecutor.executeTerminalCommandForString(command)
        return if (response.contains("not found") || response.contains("Error")){
            RequestResponse(false,response,false)
        } else{
            RequestResponse(true,response,true)
        }
    }
}