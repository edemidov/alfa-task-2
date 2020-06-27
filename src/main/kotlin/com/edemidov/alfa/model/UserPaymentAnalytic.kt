package com.edemidov.alfa.model

import java.math.BigDecimal

class UserPaymentAnalytic(val userId: String) {
    val analyticInfo: MutableMap<String, PaymentCategoryInfo> = HashMap()
    var totalSum: BigDecimal = BigDecimal.ZERO
}