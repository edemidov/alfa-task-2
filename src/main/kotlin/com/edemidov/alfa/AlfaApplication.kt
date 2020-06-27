package com.edemidov.alfa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlfaApplication

fun main(args: Array<String>) {
	runApplication<AlfaApplication>(*args)
}
