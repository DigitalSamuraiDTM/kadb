package com.digitalsamurai.kadb.client.provider.commands.shell.packagemanager

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface PackageManager {
    suspend fun getListPackages(device : Device) : RequestResponse<List<String>>
    suspend fun getListPackages(serial : String) : RequestResponse<List<String>>
}