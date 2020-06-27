package com.edemidov.alfa.messaging

class ParseException(entityName: String, value: String, cause: Exception? = null)
    : IllegalArgumentException("Failed to parse $entityName: $value", cause)