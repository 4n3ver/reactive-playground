package space.yoelivan.stockquotes.domains

import java.math.BigDecimal
import java.math.MathContext
import java.time.Instant

private val MATH_CTX = MathContext(2)

data class Quote(
        val ticker: String,
        val price: BigDecimal,
        val instant: Instant
) {
    constructor(ticker: String, price: Double, instant: Instant) :
            this(ticker, BigDecimal(price, MATH_CTX), instant)

    constructor(ticker: String, price: BigDecimal) :
            this(ticker, price, Instant.now())

    constructor(ticker: String, price: Double) :
            this(ticker, price, Instant.now())

    fun updatePrice(price: BigDecimal): Quote =
            Quote(ticker, price, Instant.now())

    fun updatePrice(price: Double): Quote =
            Quote(ticker, price, Instant.now())
}
