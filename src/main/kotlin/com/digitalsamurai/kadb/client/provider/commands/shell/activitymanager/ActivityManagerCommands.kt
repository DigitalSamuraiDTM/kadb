package com.digitalsamurai.kadb.client.provider.commands.shell.activitymanager

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.requestresponse.BatteryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface ActivityManagerCommands {
    suspend fun startApplicationOnDevice(device: Device, application: String): RequestResponse<Boolean>

    suspend fun startApplicationOnDevice(serial: String, application: String): RequestResponse<Boolean>

}