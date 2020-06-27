package com.edemidov.alfa.messaging

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

const val DATA_INPUT_CHANNEL = "dataInputChannel"

interface DataInputChannel {

    @Input(DATA_INPUT_CHANNEL)
    fun input(): SubscribableChannel
}
