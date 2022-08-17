package com.digitalsamurai.kadb.client.provider.terminal.shell.dumpsys.battery

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.BatteryCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.entity.BatteryInfo
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.entity.OppoBatteryServiceState
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandExecutor

internal class BatteryTerminalCommandsImpl(private val adbPath : String) : BatteryCommands {

    override suspend fun resetBatteryOnDevice(device: Device): RequestResponse<Boolean> {
        return resetBatteryOnDevice(device.serial)
    }

    override suspend fun resetBatteryOnDevice(serial: String): RequestResponse<Boolean> {
        val request = listOf(adbPath,"-s",serial,"shell","dumpsys","battery", "reset")
        val response = TerminalCommandExecutor.executeTerminalCommandForString(request)
        return if (response.contains("not found")){
            RequestResponse(false,"",false)
        } else {
            RequestResponse(true,response,true)
        }
    }

    override suspend fun setBatteryLevel(device: Device, level : Int): RequestResponse<Boolean> {
        return setBatteryLevel(device.serial, level)
    }

    override suspend fun setBatteryLevel(serial: String, level : Int): RequestResponse<Boolean> {
        if (level>100 || level<0){
            return RequestResponse(false,"",false)
        }
        val request = listOf(adbPath,"-s",serial,"shell","dumpsys","battery","set","level",level.toString())
        val response = TerminalCommandExecutor.executeTerminalCommandForString(request)
        return if (response.contains("not found")){
            RequestResponse(false,"",false)
        } else {
            RequestResponse(true,response,true)
        }

    }

    override suspend fun getInfoBatteryOnDevice(device: Device): RequestResponse<BatteryInfo?> {
        return getInfoBatteryOnDevice(device.serial)
    }

    override suspend fun getInfoBatteryOnDevice(serial: String): RequestResponse<BatteryInfo?> {
        val request = listOf(adbPath,"-s",serial,"shell","dumpsys","battery")
        val response = TerminalCommandExecutor.executeTerminalCommandForString(request)
        if (response.contains("not found")) {
            var batteryInfo = BatteryInfo(
                voltage = response.substringAfter("voltage: ").substringBefore("\r").toInt(),
                temperature = response.substringAfter("temperature: ").substringBefore("\r").toInt(),
                level = response.substringAfter("level: ").substringBefore("\r").toInt(),
                scale = response.substringAfter("scale: ").substringBefore("\r").toInt(),
                technology = response.substringAfter("technology: ").substringBefore("\r"),
                present = response.substringAfter("present: ").substringBefore("\r").toBoolean(),
                status = response.substringAfter("status: ").substringBefore("\r").toInt(),
                acPowered = response.substringAfter("AC powered: ").substringBefore("\r").toBoolean(),
                usbPowered = response.substringAfter("USB powered: ").substringBefore("\r").toBoolean(),
                health = response.substringAfter("health: ").substringBefore("\r").toInt(),
                wirelessPowered = response.substringAfter("Wireless powered: ").substringBefore("\r").toBoolean(),
                oppoBatteryServiceState = if (response.contains("OPPO Battery Service state")) {
                    OppoBatteryServiceState(
                        chargerVoltage = response.substringAfter("Charger voltage : ").substringBefore("\r").toInt(),
                        BatteryCurrent = response.substringAfter("Battery current : ").substringBefore("\r").toInt(),
                        ChargerTechnology = response.substringAfter("ChargerTechnology: ").substringBefore("\r")
                            .toInt(),
                        ChargeFastCharger = response.substringAfter("ChargeFastCharger: ").substringBefore("\r")
                            .toBoolean(),
                        PlugType = response.substringAfter("PlugType: ").substringBefore("\r").toInt(),
                        UpdatesStopped = response.substringAfter("UpdatesStopped: ").substringBefore("\r").toBoolean(),
                        UsbHwStatus = response.substringAfter("UsbHwStatus: ").substringBefore("\r").toInt(),
                        BatteryHwStatus = response.substringAfter("BatteryHwStatus: ").substringBefore("\r").toInt(),
                        HwStatusIsSet = response.substringAfter("HwStatusIsSet: ").substringBefore("\r").toInt(),
                        BatteryIcStatus = response.substringAfter("BatteryIcStatus: ").substringBefore("\r").toInt(),
                        IcStatusIsSet = response.substringAfter("IcStatusIsSet: ").substringBefore("\r").toInt(),
                        mUsbStatus = response.substringAfter("mUsbStatus: ").substringBefore("\r").toInt(),
                        PhoneTemp = response.substringAfter("PhoneTemp: ").substringBefore("\r").toInt(),
                        ThermalFeatureOn = response.substringAfter("ThermalFeatureOn: ").substringBefore("\r")
                            .toBoolean(),
                    )
                } else {
                    null
                }
            )
            return RequestResponse(true, response, batteryInfo)
        } else{
            return RequestResponse(false,response,null)
        }
    }

}