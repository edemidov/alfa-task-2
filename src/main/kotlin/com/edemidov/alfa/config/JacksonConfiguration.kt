package com.edemidov.alfa.config

import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZonedDateTime

@Configuration
class JacksonConfiguration {
    @Bean
    @ConditionalOnMissingBean(ThreeTenModule::class)
    fun threeTenModule(): ThreeTenModule {
        val module = ThreeTenModule()
        module.addDeserializer(Instant::class.java, CustomInstantDeserializer.INSTANT)
        module.addDeserializer(OffsetDateTime::class.java, CustomInstantDeserializer.OFFSET_DATE_TIME)
        module.addDeserializer(ZonedDateTime::class.java, CustomInstantDeserializer.ZONED_DATE_TIME)
        return module
    }
}