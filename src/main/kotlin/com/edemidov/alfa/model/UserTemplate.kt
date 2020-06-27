package com.edemidov.alfa.model

import java.math.BigDecimal

data class UserTemplate (
    val amount: BigDecimal,
    val categoryId: Int,
    val recipientId: String
)