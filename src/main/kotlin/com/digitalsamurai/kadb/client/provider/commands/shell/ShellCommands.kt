package com.digitalsamurai.kadb.client.provider.commands.shell

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.activitymanager.ActivityManagerCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.uiautomator.UiautomatorCommands
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

interface ShellCommands {
    val UiAutomator : UiautomatorCommands
    val ActivityManager : ActivityManagerCommands

}