package com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.battery.entity

data class BatteryInfo(
    val voltage : Int,
    val temperature : Int,
    val level : Int,
    val scale : Int,
    val technology : String,
    val present : Boolean,
    val status : Int,
    val acPowered : Boolean,
    val usbPowered : Boolean,
    val health : Int,
    val wirelessPowered : Boolean,

    //custom info
    val oppoBatteryServiceState: OppoBatteryServiceState?

    /**
     * may be other companies like Meizu,xiaomi or etc has the same custom info about battery, but I dont know how to check it
     * */

)
