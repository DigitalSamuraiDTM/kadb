package com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.entity.BatteryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface BatteryCommands {

    //adb -s "serial" shell dumpsys battery set level 0-100
    suspend fun setBatteryLevel(device: Device, level : Int) : RequestResponse<Boolean>
    suspend fun setBatteryLevel(serial: String, level : Int) : RequestResponse<Boolean>

    //adb -s "serial" shell dumpsys battery
    suspend fun getInfoBatteryOnDevice(device : Device) : RequestResponse<BatteryInfo?>
    suspend fun getInfoBatteryOnDevice(serial : String) : RequestResponse<BatteryInfo?>

    //adb -s "serial" shell dumpsys battery reset
    suspend fun resetBatteryOnDevice(device: Device) : RequestResponse<Boolean>
    suspend fun resetBatteryOnDevice(serial: String) : RequestResponse<Boolean>
}