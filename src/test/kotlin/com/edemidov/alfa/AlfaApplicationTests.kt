package com.edemidov.alfa

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Disabled
class AlfaApplicationTests {

	@Test
	fun contextLoads() {
		println("This is test!")
	}
}
