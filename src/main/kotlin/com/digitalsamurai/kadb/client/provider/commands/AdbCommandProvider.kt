package com.digitalsamurai.kadb.client.provider.commands

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.ShellCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse
import kotlinx.coroutines.flow.Flow

interface AdbCommandProvider {

    val Shell : ShellCommands
    suspend fun sendCommand(command : String) : RequestResponse<String>

    suspend fun getDevices() : RequestResponse<List<Device>>

    suspend fun installApkOnDevice(device: Device, path : String) : RequestResponse<Boolean>
    suspend fun installApkOnDevice(serial: String , path : String): RequestResponse<Boolean>

    suspend fun uninstallApkOnDevice(device: Device, packageName : String) // TODO: 11.05.2022
    suspend fun uninstallApkOnDevice(serial: String, packageName : String) // TODO: 11.05.2022

    suspend fun getDeviceBySerial(serial :String) : RequestResponse<Device?>

    //adb pull -a /sdcard/screen.png D:/work/screen.png
    suspend fun pullFileFromDevice(device: Device, deviceFilePath : String, savePath : String?=null) : RequestResponse<Pair<Int, Int>?>
    suspend fun pullFileFromDevice(serial: String , deviceFilePath : String, savePath : String?=null): RequestResponse<Pair<Int, Int>?>

    //adb -s "serial" reboot
    suspend fun rebootDevice(device: Device) : RequestResponse<Boolean>
    suspend fun rebootDevice(serial: String) : RequestResponse<Boolean>

    //adb -s "serial" logcat
    suspend fun observeLogcatDevice(device: Device) : RequestResponse<Flow<String>>
    suspend fun observeLogcatDevice(serial: String) : RequestResponse<Flow<String>>


}