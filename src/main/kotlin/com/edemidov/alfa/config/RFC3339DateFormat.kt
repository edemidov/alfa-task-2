package com.edemidov.alfa.config

import com.fasterxml.jackson.databind.util.ISO8601DateFormat
import com.fasterxml.jackson.databind.util.ISO8601Utils
import com.fasterxml.jackson.databind.util.StdDateFormat
import java.text.FieldPosition
import java.util.*

class RFC3339DateFormat : StdDateFormat() {

    override fun format(date: Date, toAppendTo: StringBuffer, fieldPosition: FieldPosition): StringBuffer {
        val value = ISO8601Utils.format(date, true)
        toAppendTo.append(value)
        return toAppendTo
    }
}