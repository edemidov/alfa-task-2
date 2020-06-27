package com.edemidov.alfa.api

import com.edemidov.alfa.service.PaymentAnalyticsService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class UserAnalyticsHandler(private val paymentAnalyticsService: PaymentAnalyticsService) {

    fun fetchAllUserAnalyticsInfo(request: ServerRequest) =
        ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .syncBody(paymentAnalyticsService.getAllUserInfo())

    fun fetchUserAnalyticsInfoByUserId(request: ServerRequest) =
        paymentAnalyticsService.getUserInfoById(request.pathVariable("userId"))
            ?.let {
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .syncBody(it)
            }
            ?: userNotFoundResponse()

    fun fetchUserStatisticsByUserId(request: ServerRequest) =
        paymentAnalyticsService.getUserStatistics(request.pathVariable("userId"))
            ?.let {
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .syncBody(it)
            }
            ?: userNotFoundResponse()

    fun fetchTemplatesByUserId(request: ServerRequest) =
        paymentAnalyticsService.getUserTemplates(request.pathVariable("userId"))
            ?.let {
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .syncBody(it)
            }
            ?: userNotFoundResponse()

    private fun userNotFoundResponse() =
        ServerResponse.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .syncBody("""{"status": "user not found"}""")
}