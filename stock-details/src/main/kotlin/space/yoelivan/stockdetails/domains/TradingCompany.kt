package space.yoelivan.stockdetails.domains

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class TradingCompany(
        @Id
        val ticker: String,
        val description: String
)
