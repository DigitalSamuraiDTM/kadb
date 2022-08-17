package com.digitalsamurai.kadb.client.provider.terminal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

internal object TerminalCommandExecutor{



    fun  executeTerminalCommandForString(commandData : List<String>) : String {
        val reader = getReaderFromCommand(commandData)
        return reader.readText()
    }

    fun  executeTerminalCommandForArray(commandData : List<String>) : List<String> {
        val reader = getReaderFromCommand(commandData)
        return reader.lines().toList()
    }

    suspend fun observeTerminalCommand(commandData : List<String>) : BufferedReader {
        return getReaderFromCommand(commandData)
    }


    private fun getReaderFromCommand(commandData: List<String>) : BufferedReader{
        val build = ProcessBuilder(commandData)
        return BufferedReader(InputStreamReader(build.start().inputStream))
    }



}