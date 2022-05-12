package com.digitalsamurai.kadb.client.provider.terminal.shell

import com.digitalsamurai.kadb.client.Device
import com.digitalsamurai.kadb.client.provider.commands.shell.ShellCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.activitymanager.ActivityManagerCommands
import com.digitalsamurai.kadb.client.provider.commands.shell.uiautomator.UiautomatorCommands
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandExecutor
import com.digitalsamurai.kadb.client.provider.requestresponse.RequestResponse

class ShellTerminalCommandsImpl(private val adbPath : String,
                                override val ActivityManager : ActivityManagerCommands,
                                override val UiAutomator: UiautomatorCommands) : ShellCommands {


}