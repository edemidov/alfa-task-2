package com.edemidov.alfa.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal

class PaymentCategoryInfo {
    @JsonIgnore
    var counter: Int = 0
    var max: BigDecimal = BigDecimal.ZERO
    var min: BigDecimal = BigDecimal.ZERO
    var sum: BigDecimal = BigDecimal.ZERO
}