package com.digitalsamurai.kadb.client

class Device {
    var serial : String
        private set
    var status : DeviceStatus? = null
        private set

    var product : String? = null
        private set

    var model : String?  = null
        private set

    var device : String? = null
        private set

    var transportId : Int? = null
        private set



    constructor(serial: String, status : DeviceStatus, product : String?, model : String?, device : String?, transportId :Int?) {
        this.serial = serial
        this.status = status
        this.product = product
        this.model = model
        this.device = device
        this.transportId = transportId
    }
    constructor(serial : String){
        this.serial = serial
    }

    enum class DeviceStatus{
        OK, OFFLINE, UNAUTHORIZED, UNKNOWN, RECOVERY, SIDELOAD
    }

    override fun toString(): String {
        val out = "serial:${serial}\n" +
                "status:${status}\n" +
                "product:${product}\n" +
                "model:${model}\n" +
                "device:${device}\n" +
                "transportId:${transportId}"
        return out
    }



}