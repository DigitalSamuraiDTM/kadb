package com.digitalsamurai.kadb.client.provider.terminal

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.AdbCommandProvider
import com.digitalsamurai.kadb.client.provider.commands.shell.ShellCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

class TerminalCommandProvider internal constructor(path : String,override val Shell: ShellCommands) : AdbCommandProvider {

    private var adbPath = path


    override suspend fun sendCommand(command: String): RequestResponse<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getDevices(): RequestResponse<List<Device>> {
        val command = arrayListOf(adbPath,"devices", "-l")
        val out = TerminalCommandExecutor.executeTerminalCommandForArray(command)
        val devicesList = ArrayList<Device>()

        out.subList(1,out.size-1).forEach {
            if (it.isNotEmpty()){
                devicesList.add(parseDeviceFromString(it))
            }
        }
        return RequestResponse(true,out.joinToString("\n"),devicesList)
    }

    override suspend fun getDeviceBySerial(serial: String): RequestResponse<Device?> {
        val command = arrayListOf(adbPath,"devices", "-l")
        val out = TerminalCommandExecutor.executeTerminalCommandForArray(command)
        val deviceString = out.find { it.contains(serial) }
        return if (deviceString==null){
            return RequestResponse(false,"Device not found",null)
        } else{
            return RequestResponse(true,deviceString,parseDeviceFromString(deviceString))
        }
    }

    override suspend  fun  installApkOnDevice(serial: String, filePath: String) : RequestResponse<Boolean>  {
        val command = arrayListOf(adbPath,"-s",serial, "install", filePath)
        val result = TerminalCommandExecutor.executeTerminalCommandForArray(command)
        return if (result[1]=="Success"){
            RequestResponse(true,result.joinToString("\n"),true)
        } else{
            RequestResponse(false,result.joinToString("\n"),false)
        }
    }


    override suspend fun pullFileFromDevice(serial: String, deviceFilePath: String, savePath: String?): RequestResponse<Pair<Int,Int>?> {
        val answer = TerminalCommandExecutor.executeTerminalCommandForArray(arrayListOf(adbPath, "pull", deviceFilePath).also {
            if (savePath!=null){it.add(savePath)}
        })
        return if (answer.size==1){
            val pulledSize = answer[0].substringBefore(" file pulled").substringAfter(": ").toInt()
            val skippedSize = answer[0].substringBefore(" skipped").substringAfterLast(", ").toInt()
            RequestResponse(true,answer.joinToString("\n"),Pair(pulledSize,skippedSize))
        } else{
            RequestResponse(false,answer.joinToString("\n"),null)
        }
    }

    override suspend fun uninstallApkOnDevice(serial: String, path: String) {
        TODO("Not yet implemented")
    }

    override suspend fun installApkOnDevice(device: Device, filePath: String) : RequestResponse<Boolean> {
        return installApkOnDevice(device.serial,filePath)
    }

    override suspend fun pullFileFromDevice(device: Device, deviceFilePath: String, savePath : String?): RequestResponse<Pair<Int,Int>?> {
        return pullFileFromDevice(device.serial,deviceFilePath,savePath)
    }


    override suspend fun uninstallApkOnDevice(device: Device, path: String) {
        uninstallApkOnDevice(device.serial,path)
    }




    private fun parseDeviceFromString(deviceString : String) : Device {
        val serial = deviceString.substringBefore(" ")
        val product = if (deviceString.contains("product:")){deviceString.substringAfter("product:").substringBefore(" ")} else {null}
        val model = if (deviceString.contains("model:")){deviceString.substringAfter("model:").substringBefore(" ")} else{null}
        val device = if (deviceString.contains("device:")){deviceString.substringAfter("device:").substringBefore(" ")} else {null}
        val transport = if (deviceString.contains("transport_id:")){deviceString.substringAfter("transport_id:").substringBefore(" ")} else { null}
        val status : Device.DeviceStatus =
            if (deviceString.contains("device")){
                Device.DeviceStatus.OK
            } else if(deviceString.contains("offline")){
                Device.DeviceStatus.OFFLINE
            } else if (deviceString.contains("unauthorized")){
                Device.DeviceStatus.UNAUTHORIZED
            } else if (deviceString.contains("recovery")){
                Device.DeviceStatus.RECOVERY
            } else if (deviceString.contains("sideload")){
                Device.DeviceStatus.SIDELOAD
            } else{
                Device.DeviceStatus.UNKNOWN
            }
        return Device(serial = serial, status = status,
            product = product, model = model,
            device = device, transportId = transport?.toIntOrNull())
    }
}