package com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.entity

data class OppoBatteryServiceState (
    val chargerVoltage : Int,
    val BatteryCurrent : Int,
    val ChargerTechnology: Int,
    val ChargeFastCharger: Boolean,
    val PlugType: Int,
    val UpdatesStopped: Boolean,
    val UsbHwStatus: Int,
    val BatteryHwStatus: Int,
    val HwStatusIsSet: Int,
    val BatteryIcStatus: Int,
    val IcStatusIsSet: Int,

    val mUsbStatus : Int,
    val PhoneTemp : Int,
    val ThermalFeatureOn : Boolean
)