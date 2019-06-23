package space.yoelivan.stockquotes.services

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import space.yoelivan.stockquotes.domains.Quote
import java.math.BigDecimal
import java.math.MathContext
import java.time.Duration
import kotlin.random.Random

@Service
class QuoteGeneratorService : QuoteService {
    private val MATH_CTX = MathContext(2)
    private val prices: MutableList<Quote> = mutableListOf()
    private val random: Random = Random(System.currentTimeMillis())

    init {
        prices.add(Quote("CTXS", 82.26))
        prices.add(Quote("DELL", 63.74))
        prices.add(Quote("GOOG", 847.24))
        prices.add(Quote("MSFT", 65.11))
        prices.add(Quote("ORCL", 45.71))
        prices.add(Quote("RHT", 84.29))
        prices.add(Quote("VMW", 92.21))
    }

    override fun fetchQuote(period: Duration): Flux<Quote> {
        return Flux.interval(period)
                .onBackpressureDrop()
                .flatMap { Flux.fromIterable(prices) }
                .map { it.updatePrice(it.price * randomFluctuation()) }
    }

    private fun randomFluctuation(): BigDecimal =
            BigDecimal(0.05 * random.nextDouble(), MATH_CTX)
}
