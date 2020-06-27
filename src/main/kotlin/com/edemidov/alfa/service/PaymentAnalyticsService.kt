package com.edemidov.alfa.service

import com.edemidov.alfa.messaging.PaymentDto
import com.edemidov.alfa.model.PaymentCategoryInfo
import com.edemidov.alfa.model.UserPaymentAnalytic
import com.edemidov.alfa.model.UserPaymentStats
import com.edemidov.alfa.model.UserTemplate
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PaymentAnalyticsService {

    private val userAnalyticsData = HashMap<String, UserPaymentAnalytic>()
    private val userTemplates = HashMap<String, Map<UserTemplate, Int>>()

    fun getAllUserInfo(): Collection<UserPaymentAnalytic> = userAnalyticsData.values

    fun getUserInfoById(userId: String): UserPaymentAnalytic? = userAnalyticsData[userId]

    fun getUserStatistics(userId: String): UserPaymentStats? =
        userAnalyticsData[userId]?.let { userPaymentAnalytic ->
            val analyticInfo = userPaymentAnalytic.analyticInfo
            val categoryWithMaxSum = analyticInfo.maxBy { it.value.sum }!!.key.toInt()
            val categoryWithMinSum = analyticInfo.minBy { it.value.sum }!!.key.toInt()
            val oftenCategoryId = analyticInfo.maxBy { it.value.counter }!!.key.toInt()
            val rareCategoryId = analyticInfo.minBy { it.value.counter }!!.key.toInt()
            UserPaymentStats(categoryWithMaxSum, categoryWithMinSum, oftenCategoryId, rareCategoryId)
        }

    fun getUserTemplates(userId: String): List<UserTemplate>? =
        userTemplates[userId]?.let { templates ->
            templates.entries.filter { it.value > 2 }.map { it.key }
        }

    fun import(payment: PaymentDto) {
        val (categoryId, userId, amount, _) = payment
        val userPaymentAnalytic = userAnalyticsData.computeIfAbsent(userId) { UserPaymentAnalytic(userId) }
        userPaymentAnalytic.totalSum = userPaymentAnalytic.totalSum + amount
        updateCategoryInfo(userPaymentAnalytic.analyticInfo.computeIfAbsent(categoryId) { PaymentCategoryInfo() }, categoryId, amount)
        addTemplate(payment)
    }

    private fun addTemplate(payment: PaymentDto) {
        val templates = userTemplates.computeIfAbsent(payment.userId) { HashMap() } as MutableMap
        val userTemplate = UserTemplate(payment.amount, payment.categoryId.toInt(), payment.recipientId)
        if (userTemplate !in templates) {
            templates[userTemplate] = 1
        } else {
            templates[userTemplate] = templates[userTemplate]!! + 1
        }
    }

    private fun updateCategoryInfo(categoryInfo: PaymentCategoryInfo, categoryId: String, amount: BigDecimal) {
        if (categoryInfo.max < amount) {
            categoryInfo.max = amount
        }
        if (categoryInfo.min == BigDecimal.ZERO || categoryInfo.min > amount) {
            categoryInfo.min = amount
        }
        categoryInfo.sum = categoryInfo.sum + amount
        categoryInfo.counter++
    }
}