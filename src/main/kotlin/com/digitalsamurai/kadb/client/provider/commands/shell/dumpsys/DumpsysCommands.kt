package com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.requestresponse.BatteryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.MemoryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface DumpsysCommands {
    suspend fun getInfoMemoryOnDevice(device : Device) : RequestResponse<MemoryInfo> // TODO: 13.05.2022  
    suspend fun getInfoMemoryOnDevice(serial : String) : RequestResponse<MemoryInfo> // TODO: 13.05.2022  
    
    suspend fun getInfoBatteryOnDevice(device : Device) : RequestResponse<BatteryInfo> // TODO: 13.05.2022
    suspend fun getInfoBatteryOnDevice(serial : String) : RequestResponse<BatteryInfo> // TODO: 13.05.2022  
}