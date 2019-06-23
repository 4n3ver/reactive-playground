package space.yoelivan.tradingservice.domains

import java.math.BigDecimal
import java.time.Instant

data class Quote(
        val ticker: String,
        val price: BigDecimal,
        val instant: Instant
)
