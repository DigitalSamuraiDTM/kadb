package com.digitalsamurai.kadb.client

import com.digitalsamurai.kadb.client.provider.commands.AdbCommandProvider
import com.digitalsamurai.kadb.client.provider.commands.shell.dumpsys.DumpsysCommands
import com.digitalsamurai.kadb.client.provider.terminal.shell.ShellTerminalCommandsImpl
import com.digitalsamurai.kadb.client.provider.terminal.TerminalCommandProvider
import com.digitalsamurai.kadb.client.provider.terminal.shell.activitymanager.ActivityManagerTerminalCommandsImpl
import com.digitalsamurai.kadb.client.provider.terminal.shell.dumpsys.DumpsysTerminalCommandsImpl
import com.digitalsamurai.kadb.client.provider.terminal.shell.dumpsys.battery.BatteryTerminalCommandsImpl
import com.digitalsamurai.kadb.client.provider.terminal.shell.uiautomator.UiAutomatorTerminalCommandsImpl

class AdbClientBuilder {

    /**
     * if u have adb in environment u can skip [adbPath] argument
     */
    class Builder(adbPath : String = ""){
        private var adbPath : String = "${adbPath}\\adb"
        private var commandProvider : AdbCommandProvider
        private var isAuto = false

        //for default use terminal for providing requests
        init {
            commandProvider  = TerminalCommandProvider(this.adbPath,
                ShellTerminalCommandsImpl(this.adbPath,ActivityManagerTerminalCommandsImpl(this.adbPath),
                    UiAutomatorTerminalCommandsImpl(this.adbPath),
                    DumpsysTerminalCommandsImpl(this.adbPath, BatteryTerminalCommandsImpl(this.adbPath))))
        }

        private fun setAutoStartAdbServer(isAuto : Boolean) : Builder{
            this.isAuto = isAuto
            return this
        }

        fun setCommandProvider(typeProvider : CommandProvider) : Builder{
            when(typeProvider){
                CommandProvider.TERMINAL->{
                    this.commandProvider = TerminalCommandProvider(adbPath,
                        ShellTerminalCommandsImpl(adbPath,ActivityManagerTerminalCommandsImpl(this.adbPath),
                            UiAutomatorTerminalCommandsImpl(this.adbPath),
                            DumpsysTerminalCommandsImpl(adbPath,BatteryTerminalCommandsImpl(this.adbPath))))
                }
//                CommandProvider.NETWORK->{
//                    this.commandProvider = NetworkCommandProvider()
//                }
            }

            return this
        }



        fun build() : AdbClient {
            val client = AdbClient(adbPath,commandProvider)
            return client
        }
    }
    enum class CommandProvider{
        TERMINAL
    }
}