package com.digitalsamurai.kadb.client.provider.terminal.shell.activitymanager

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.activitymanager.ActivityManagerCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandExecutor

class ActivityManagerTerminalCommandsImpl(val adbPath : String) : ActivityManagerCommands {

    /**
     * get [application] like com.package.name/com.package.name.ActivityName
     */
    override suspend fun startApplicationOnDevice(device: Device, application: String): RequestResponse<Boolean> {
        return startApplicationOnDevice(device.serial,application)
    }

    /**
     * get [application] like com.package.name/com.package.name.ActivityName
     */
    override suspend fun startApplicationOnDevice(serial: String, application: String): RequestResponse<Boolean> {
        val command = arrayListOf(adbPath,"-s",serial,"shell","am","start","-n",application)
        val answer = TerminalCommandExecutor.executeTerminalCommandForArray(command)
        if (answer.size>1){
            if (answer.get(1).contains("Error") || answer.size>=3){
                return RequestResponse(false,answer.joinToString("\n"),false)
            } else if (answer.get(1).contains("Warning")){
                return RequestResponse(true,answer.joinToString("\n"),true)
            }
        } else{
            return RequestResponse(true,answer.joinToString("\n"),true)
        }
        return RequestResponse(true,answer.joinToString("\n"),true)
    }
}