package com.digitalsamurai.kadb.client.provider.terminal

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.AdbCommandProvider
import com.digitalsamurai.kadb.client.provider.commands.shell.ShellCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TerminalCommandProvider internal constructor(path : String,override val Shell: ShellCommands) : AdbCommandProvider {

    private var adbPath = path


    /**
     * send [command] without word "adb".
     *  Don't need device or serial because user can send it.
     *  Always return true because doesn't have any filters or mappers
     */
    override suspend fun sendCommand(command: String): RequestResponse<String> {
        val answer = TerminalCommandExecutor.executeTerminalCommandForString(arrayListOf(adbPath,command))
        return RequestResponse(true,answer,answer)
    }

    override suspend fun rebootDevice(device: Device): RequestResponse<Boolean> {
        return rebootDevice(device.serial)
    }

    override suspend fun rebootDevice(serial: String): RequestResponse<Boolean> {
        val command = arrayListOf(adbPath,"-s",serial,"reboot")
        val out = TerminalCommandExecutor.executeTerminalCommandForArray(command)
        return RequestResponse(true,"",true)
    }

    override suspend fun observeLogcatDevice(device: Device): RequestResponse<Flow<String>> {
        return observeLogcatDevice(device.serial)
    }

    override suspend fun observeLogcatDevice(serial: String): RequestResponse<Flow<String>> {
        val command = listOf(adbPath,"-s",serial,"logcat")
        val reader = TerminalCommandExecutor.observeTerminalCommand(command)

        var o : String? = null
        val flow = flow {
            do {
                o = reader.readLine()
                if (o != null) {
                    emit(o!!)
                }
            } while (o != null)
        }.flowOn(Dispatchers.IO)
        return  RequestResponse(true, clearAnswer = "",flow)
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
            RequestResponse(false,"Device not found",null)
        } else{
            RequestResponse(true,deviceString,parseDeviceFromString(deviceString))
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
        val answer = TerminalCommandExecutor.executeTerminalCommandForArray(arrayListOf(adbPath,"-s",serial, "pull", deviceFilePath).also {
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

    override suspend fun uninstallApkOnDevice(serial: String, packageName: String) {
        val answer = TerminalCommandExecutor.executeTerminalCommandForArray(arrayListOf(adbPath,"-s",serial,"uninstall", packageName))
    }

    override suspend fun installApkOnDevice(device: Device, path: String) : RequestResponse<Boolean> {
        return installApkOnDevice(device.serial,path)
    }

    override suspend fun pullFileFromDevice(device: Device, deviceFilePath: String, savePath : String?): RequestResponse<Pair<Int,Int>?> {
        return pullFileFromDevice(device.serial,deviceFilePath,savePath)
    }


    override suspend fun uninstallApkOnDevice(device: Device, packageName: String) {
        uninstallApkOnDevice(device.serial,packageName)
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