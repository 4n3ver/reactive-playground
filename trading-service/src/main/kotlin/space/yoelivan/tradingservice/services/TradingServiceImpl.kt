package space.yoelivan.tradingservice.services

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import space.yoelivan.tradingservice.clients.QuotesClient
import space.yoelivan.tradingservice.clients.TradingCompanyClient
import space.yoelivan.tradingservice.domains.Quote
import space.yoelivan.tradingservice.domains.TradingCompanySummary

@Service
class TradingServiceImpl(
        val quotesClient: QuotesClient,
        val tradingCompanyClient: TradingCompanyClient
) : TradingService {
    override fun getQuotesFeed(): Flux<Quote> = quotesClient.getQuotesFeed()

    override fun getQuoteDetails(ticker: String): Mono<TradingCompanySummary> =
            tradingCompanyClient.getCompany(ticker)
                    .zipWith(quotesClient.getLatestQuote(ticker))
                    .map { TradingCompanySummary(it.t2, it.t1) }
}
