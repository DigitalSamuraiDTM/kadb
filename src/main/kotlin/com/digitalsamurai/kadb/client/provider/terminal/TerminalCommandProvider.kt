package com.digitalsamurai.kadb.client.provider.terminal

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.AdbCommandProvider
import com.digitalsamurai.kadb.client.provider.TerminalCommandExecutor

class TerminalCommandProvider : AdbCommandProvider {
    private var commandExecutor = TerminalCommandExecutor()
    private var adbPath = "adb"
    init {

    }

    override suspend fun sendCommand(command: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getDevices(): List<Device> {
        val command = arrayListOf(adbPath,"devices", "-l")
        val out = commandExecutor.executeTerminalCommandForArray(command)
        val devicesList = ArrayList<Device>()

        out.subList(1,out.size-1).forEach {
            if (it.isNotEmpty()){
                devicesList.add(parseDeviceFromString(it))
            }
        }
        return devicesList
    }

    override suspend fun installApkOnDevice(device: Device, filePath: String) {
        installApkOnDevice(device.serial,filePath)
    }

    override suspend fun installApkOnDevice(serial: String, filePath: String) {
        val prefix = "-s"
        val command = arrayListOf(adbPath,prefix,serial, "install", filePath)
        val result = commandExecutor.executeTerminalCommandForArray(command)
        if (result[1]=="Success"){
            // TODO: 11.05.2022 RETURN ZBS
            println("ZBS")
        } else{
            // TODO: 11.05.2022 SET EXCEPTION
            println("SOME ERROR")
        }
    }

    override suspend fun getDeviceBySerial(serial: String): Device? {
        val command = arrayListOf(adbPath,"devices", "-l")
        val out = commandExecutor.executeTerminalCommandForArray(command)
        val deviceString = out.find { it.contains(serial) }
        return if (deviceString==null){
            null
        } else{
            parseDeviceFromString(deviceString)
        }
    }

    fun setExecutorPath(adbPath: String) {
        this.adbPath = "${adbPath}adb"
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