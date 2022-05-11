package com.digitalsamurai.kadb.client.provider

import com.digitalsamurai.kadb.client.Device

interface AdbCommandProvider {
    suspend fun sendCommand(command : String) : String

    suspend fun getDevices() : List<Device>

    suspend fun installApkOnDevice(device: Device, path : String)
    suspend fun installApkOnDevice(serial: String , path : String) 
    
    suspend fun uninstallApkOnDevice(device: Device, path : String) // TODO: 11.05.2022  
    suspend fun uninstallApkOnDevice(serial: String , path : String) // TODO: 11.05.2022  

    suspend fun getDeviceBySerial(serial :String) : Device?

    suspend fun startApplicationOnDevice(device: Device, application:String) // TODO: 11.05.2022  
    suspend fun startApplicationOnDevice(serial: String, application:String) // TODO: 11.05.2022  

}