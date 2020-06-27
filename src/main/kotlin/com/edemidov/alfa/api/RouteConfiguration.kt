package com.edemidov.alfa.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RouteConfiguration {

    @Bean
    fun analytics(userAnalyticsHandler: UserAnalyticsHandler) = router {
        GET("/analytic", userAnalyticsHandler::fetchAllUserAnalyticsInfo)
        GET("/analytic/{userId}", userAnalyticsHandler::fetchUserAnalyticsInfoByUserId)
        GET("/analytic/{userId}/stats", userAnalyticsHandler::fetchUserStatisticsByUserId)
        GET("/analytic/{userId}/templates", userAnalyticsHandler::fetchTemplatesByUserId)
    }

    @Bean
    fun adminEndpoints(userAnalyticsHandler: UserAnalyticsHandler) = router {
        GET("/admin/health").invoke { ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).syncBody("""{"status": "UP"}""") }
    }
}