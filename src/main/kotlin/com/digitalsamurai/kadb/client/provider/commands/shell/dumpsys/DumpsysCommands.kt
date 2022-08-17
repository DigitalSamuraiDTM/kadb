package com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.BatteryCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.BatteryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.MemoryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface DumpsysCommands {
    
    val Battery : BatteryCommands
    
    suspend fun getInfoMemoryOnDevice(device : Device) : RequestResponse<MemoryInfo> // TODO: 13.05.2022  
    suspend fun getInfoMemoryOnDevice(serial : String) : RequestResponse<MemoryInfo> // TODO: 13.05.2022  
    



    
}