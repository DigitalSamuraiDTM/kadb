package com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.requestresponse.BatteryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface BatteryCommands {

    //adb -s "serial" shell dumpsys battery set level 0-100
    suspend fun setBatteryLevel(device: Device) : RequestResponse<Boolean> // TODO: 17.08.2022
    suspend fun setBatteryLevel(serial: String) : RequestResponse<Boolean> // TODO: 17.08.2022

    //adb -s "serial" shell dumpsys battery
    suspend fun getInfoBatteryOnDevice(device : Device) : RequestResponse<BatteryInfo> // TODO: 13.05.2022
    suspend fun getInfoBatteryOnDevice(serial : String) : RequestResponse<BatteryInfo> // TODO: 13.05.2022

}