package com.digitalsamurai.kadb.client.provider.terminal

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

internal object TerminalCommandExecutor{



    fun  executeTerminalCommandForString(commandData : ArrayList<String>) : String {
        val reader = getReaderFromCommand(commandData)
        return reader.readText()
    }
    fun  executeTerminalCommandForArray(commandData : ArrayList<String>) : List<String> {
        val reader = getReaderFromCommand(commandData)
        return reader.lines().toList()
    }

    private fun getReaderFromCommand(commandData: ArrayList<String>) : BufferedReader{
        val build = ProcessBuilder(commandData)
        return BufferedReader(InputStreamReader(build.start().inputStream))
    }



}