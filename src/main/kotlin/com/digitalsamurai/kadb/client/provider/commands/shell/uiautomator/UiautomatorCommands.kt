package com.digitalsamurai.kadb.client.provider.commands.shell.uiautomator

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface UiautomatorCommands {

    /**
     * [savePath] by default is /sdcard/window_dump.xml
     */
    fun dump(device :Device, savePath : String = "") : RequestResponse<String?>

    fun dump(serial :String, savePath : String = "") : RequestResponse<String?>
}