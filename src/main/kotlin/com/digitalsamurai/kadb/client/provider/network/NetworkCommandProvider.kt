package com.digitalsamurai.kadb.client.provider.network

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.AdbCommandProvider

class NetworkCommandProvider() : AdbCommandProvider {
    override suspend fun sendCommand(command: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getDevices(): List<Device> {
        TODO("Not yet implemented")
    }

    override suspend fun installApkOnDevice(device: Device, path: String) {
        TODO("Not yet implemented")
    }

    override suspend fun installApkOnDevice(serial: String, path: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getDeviceBySerial(serial: String): Device? {
        TODO("Not yet implemented")
    }

}