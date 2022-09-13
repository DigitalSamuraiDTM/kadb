package com.digitalsamurai.kadb.client.provider.terminal.shell.activitymanager

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.activitymanager.ActivityManagerCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandExecutor

class ActivityManagerTerminalCommandsImpl(val adbPath : String) : ActivityManagerCommands {

    /**
     * get [application] like com.package.name/com.package.name.ActivityName
     */
    override suspend fun startApplication(device: Device, application: String): RequestResponse<Boolean> {
        return startApplication(device.serial,application)
    }

    /**
     * get [application] like com.package.name/com.package.name.ActivityName
     */
    override suspend fun startApplication(serial: String, application: String): RequestResponse<Boolean> {
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

    override suspend fun startService(device: Device, service: String): RequestResponse<Boolean> {
        return startService(device.serial, service)

    }

    override suspend fun startService(serial: String, service: String): RequestResponse<Boolean> {
        val command = arrayListOf(adbPath,"-s",serial,"shell","am","startservice",service)
        val answer = TerminalCommandExecutor.executeTerminalCommandForString(command)
        if (answer.contains("Starting") || answer.contains("Start") && !answer.contains("Error")){
            return RequestResponse(true,answer,true)
        } else{
            return RequestResponse(false,answer,false)
        }

    }

    override suspend fun stopService(device: Device, service: String): RequestResponse<Boolean> {
        return stopService(device.serial, service)
    }

    override suspend fun stopService(serial: String, service: String): RequestResponse<Boolean> {
        val command = arrayListOf(adbPath,"-s",serial,"shell","am","stopservice",service)
        val answer = TerminalCommandExecutor.executeTerminalCommandForString(command)
        if (answer.contains("Service stopped") || !answer.contains("was not running")){
            return RequestResponse(true,answer,true)
        } else{
            return RequestResponse(false,answer,false)
        }
    }

    override suspend fun startForegroundService(device: Device, service: String): RequestResponse<Boolean> {
        return startForegroundService(device.serial,service)
    }

    override suspend fun startForegroundService(serial: String, service: String): RequestResponse<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun killAll(device: Device): RequestResponse<Boolean> {
        TODO("Not yet implemented")
    }
}