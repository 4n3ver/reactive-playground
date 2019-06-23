package space.yoelivan.tradingservice.services

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import space.yoelivan.tradingservice.domains.Quote
import space.yoelivan.tradingservice.domains.TradingCompanySummary

interface TradingService {
    fun getQuotesFeed(): Flux<Quote>
    fun getQuoteDetails(ticker: String): Mono<TradingCompanySummary>
}
