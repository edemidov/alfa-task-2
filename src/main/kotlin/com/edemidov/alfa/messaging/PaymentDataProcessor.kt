package com.edemidov.alfa.messaging

import com.edemidov.alfa.getLogger
import com.edemidov.alfa.service.PaymentAnalyticsService
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink

@EnableBinding(DataInputChannel::class)
class PaymentDataProcessor(private val paymentParser: PaymentParser,
                           private val paymentAnalyticsService: PaymentAnalyticsService) {

    val log = getLogger(javaClass)

    @StreamListener(DATA_INPUT_CHANNEL)
    fun paymentDataFlux(fileArrivedEventFlux: Flux<String>) {
        fileArrivedEventFlux
            .doOnNext { log.debug("Received payment $it") }
            .handle { input: String, sink: SynchronousSink<PaymentDto> ->
                try {
                    val payment = paymentParser.parse(input)
                    sink.next(payment)
                } catch (e: Exception) {
                    log.error("Parse failure", e)
                }
            }
            .doOnNext { paymentAnalyticsService.import(it) }
            .onErrorContinue { ex, value -> log.error("Unexpected error occurred while reading payment: $value", ex) }
            .subscribe()
    }
}