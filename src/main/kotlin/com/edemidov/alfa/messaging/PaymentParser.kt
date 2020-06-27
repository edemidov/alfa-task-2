package com.edemidov.alfa.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component

@Component
class PaymentParser(private val objectMapper: ObjectMapper) {

    fun parse(paymentJson: String): PaymentDto {
        return try {
            objectMapper.readValue(paymentJson)
        } catch (ex: Exception) {
            throw ParseException("Payment", paymentJson, ex)
        }
    }
}