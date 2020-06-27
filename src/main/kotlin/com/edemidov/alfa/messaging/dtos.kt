package com.edemidov.alfa.messaging

import java.math.BigDecimal

data class PaymentDto(val categoryId: String, val userId: String, val amount: BigDecimal, val recipientId: String)