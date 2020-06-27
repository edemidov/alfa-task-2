package com.edemidov.alfa

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun getLogger(clazz: Class<*>): Logger = LogManager.getLogger(clazz)