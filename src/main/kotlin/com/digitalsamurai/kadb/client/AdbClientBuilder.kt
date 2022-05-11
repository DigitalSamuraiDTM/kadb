package com.digitalsamurai.kadb.client

import com.digitalsamurai.kadb.client.provider.AdbCommandProvider
import com.digitalsamurai.kadb.client.provider.TerminalCommandProvider

class AdbClientBuilder {
    class Builder(){
        private var adbPath : String = "adb "
        private var commandProvider : AdbCommandProvider = TerminalCommandProvider()
        private var isAuto = false

        fun setAutoStartAdbServer(isAuto : Boolean) : Builder{
            this.isAuto = isAuto
            return this
        }

        fun setCommandProvider(provider : AdbCommandProvider) : Builder{
            this.commandProvider = provider
            return this
        }

        fun setAdbPath(path : String) : Builder{
            this.adbPath = "$path "
            return this
        }

        fun build() : AdbClient{
            val client = AdbClient(adbPath,commandProvider)
            if (isAuto){
                // TODO: 06.05.2022
            }
            return client
        }
    }
}