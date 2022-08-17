package com.digitalsamurai.kadb.client.provider.terminal.shell.dumpsys.battery

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.BatteryCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.BatteryInfo
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

class BatteryTerminalCommandsImpl : BatteryCommands {
    override suspend fun setBatteryLevel(device: Device): RequestResponse<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun setBatteryLevel(serial: String): RequestResponse<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getInfoBatteryOnDevice(device: Device): RequestResponse<BatteryInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun getInfoBatteryOnDevice(serial: String): RequestResponse<BatteryInfo> {
        TODO("Not yet implemented")
    }
}