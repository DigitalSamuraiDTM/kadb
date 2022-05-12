package com.digitalsamurai.kadb.client.provider.terminal.shell.uiautomator

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.uiautomator.UiautomatorCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

class UiAutomatorTerminalCommandsImpl internal constructor(adbPath : String): UiautomatorCommands {

    override fun dump(device: Device, savePath: String): RequestResponse<String> {
        return dump(device.serial,savePath)
    }

    override fun dump(serial: String, savePath: String): RequestResponse<String> {
//        if (answer.size==2){
//            var path = answer[0].substringAfter("to: ")
//            return RequestResponse(true,answer.joinToString("\n"),path)
//        } else{
//            return RequestResponse(false,answer.joinToString("\n"),null)
//
//        }
        TODO("Not yet implemented")
    }
}