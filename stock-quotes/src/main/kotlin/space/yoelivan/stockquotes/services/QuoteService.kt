package space.yoelivan.stockquotes.services

import reactor.core.publisher.Flux
import space.yoelivan.stockquotes.domains.Quote
import java.time.Duration

interface QuoteService {
    fun fetchQuote(period: Duration): Flux<Quote>
}
