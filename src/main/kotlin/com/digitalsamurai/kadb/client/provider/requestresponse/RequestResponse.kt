package com.digitalsamurai.kadb.client.provider.requestresponse

data class RequestResponse<T>(
    var requestStatus : Boolean = true,
    val clearAnswer : String,
    var data : T
    )