package com.digitalsamurai.kadb.client.provider

import com.digitalsamurai.kadb.client.Device

interface AdbCommandProvider {
    suspend fun sendCommand(command : String) : String

    suspend fun getDevices() : List<Device>

    suspend fun installApkOnDevice(device: Device, path : String)
    suspend fun installApkOnDevice(serial: String , path : String)

    suspend fun getDeviceBySerial(serial :String) : Device?

}