package space.yoelivan.tradingservice.clients

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import space.yoelivan.tradingservice.domains.Quote
import java.time.Duration

@Component
class QuotesClient(
        @Value("\${client.stock-quote.url}")
        url: String
) : WebClientTemplate(url) {
    fun getQuotesFeed(): Flux<Quote> =
            this.httpClient.get()
                    .uri("/feed")
                    .accept(MediaType.APPLICATION_STREAM_JSON)
                    .retrieve()
                    .bodyToFlux(Quote::class.java)

    fun getLatestQuote(ticker: String): Mono<Quote> =
            getQuotesFeed().filter { q -> q.ticker.equals(ticker, ignoreCase = true) }
                    .next()
                    .timeout(Duration.ofSeconds(10), Mono.empty())
}
