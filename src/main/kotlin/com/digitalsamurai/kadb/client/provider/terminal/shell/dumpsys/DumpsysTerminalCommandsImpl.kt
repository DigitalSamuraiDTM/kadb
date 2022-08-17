package com.digitalsamurai.kadb.client.provider.terminal.shell.dumpsys

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.DumpsysCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.BatteryCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.MemoryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

class DumpsysTerminalCommandsImpl(private val adbPath : String, override val Battery: BatteryCommands) : DumpsysCommands {
    override suspend fun getInfoMemoryOnDevice(device: Device): RequestResponse<MemoryInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getInfoMemoryOnDevice(serial: String): RequestResponse<MemoryInfo> {
        TODO("Not yet implemented")
    }


}