package com.digitalsamurai.kadb.client.provider.commands.shell.input

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.input.keyevents.AdbKeyCode
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandExecutor
import java.awt.Point

class InputTerminalCommandsImpl(val adbPath : String) : InputCommands {
    override suspend fun keyEvent(device: Device, keyEvent: AdbKeyCode): RequestResponse<Boolean> {
        return keyEvent(device.serial, keyEvent)
    }

    override suspend fun keyEvent(serial: String, keyEvent: AdbKeyCode): RequestResponse<Boolean> {
        val command = listOf(adbPath, "-s", serial, "shell", "input", "keyevent", keyEvent.codeEvent.toString())
        val response = TerminalCommandExecutor.executeTerminalCommandForString(command)
        return if (response.contains("not found")) {
            RequestResponse(false, response, false)
        } else {
            RequestResponse(true, response, true)
        }
    }

    override suspend fun text(device: Device, text: String): RequestResponse<Boolean> {
        return text(device.serial, text)
    }

    override suspend fun text(serial: String, text: String): RequestResponse<Boolean> {
        val command = listOf(adbPath, "-s", serial, "shell", "input", "text", text)
        val response = TerminalCommandExecutor.executeTerminalCommandForString(command)
        return if (response.contains("not found")) {
            RequestResponse(false, response, false)
        } else {
            RequestResponse(true, response, true)
        }
    }

    override suspend fun tap(device: Device, pointTap: Point): RequestResponse<Boolean> {
        return tap(device.serial, pointTap)
    }

    override suspend fun tap(serial: String, pointTap: Point): RequestResponse<Boolean> {
        val command = listOf(adbPath, "-s", serial, "shell", "input", "tap", pointTap.x.toString(), pointTap.y.toString())
        val response = TerminalCommandExecutor.executeTerminalCommandForString(command)
        return if (response.contains("not found")) {
            RequestResponse(false, response, false)
        } else {
            RequestResponse(true, response, true)
        }
    }

    override suspend fun swipe(device: Device, pointFrom: Point, pointTo:Point, duration: Int): RequestResponse<Boolean> {
        return swipe(device.serial,pointFrom, pointTo, duration)

    }

    override suspend fun swipe(serial: String,pointFrom: Point, pointTo:Point, duration: Int): RequestResponse<Boolean> {
        val command = listOf(adbPath, "-s", serial, "shell", "input", "swipe", pointFrom.x.toString(), pointFrom.y.toString(),pointTo.x.toString(),pointTo.y.toString(),duration.toString())
        val response = TerminalCommandExecutor.executeTerminalCommandForString(command)
        return if (response.contains("not found")) {
            RequestResponse(false, response, false)
        } else {
            RequestResponse(true, response, true)
        }
    }

    override suspend fun longTap(device: Device, pointTap: Point, duration: Int): RequestResponse<Boolean> {
        return longTap(device.serial,pointTap, duration)
    }
    //long tap - is swipe with duration at the same points
    override suspend fun longTap(serial: String, pointTap: Point, duration: Int): RequestResponse<Boolean> {
        return swipe(serial,pointTap,pointTap,duration)
    }
}