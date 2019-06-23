package space.yoelivan.tradingservice.clients

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import space.yoelivan.tradingservice.domains.TradingCompany
import space.yoelivan.tradingservice.exceptions.TickerNotFoundException

@Component
class TradingCompanyClient(
        @Value("\${client.stock-details.url}")
        url: String
) : WebClientTemplate(url) {
    fun getAllCompanies(): Flux<TradingCompany> =
            this.httpClient.get()
                    .uri("/details")
                    .accept(MediaType.APPLICATION_STREAM_JSON)
                    .retrieve()
                    .bodyToFlux(TradingCompany::class.java)

    fun getCompany(ticker: String): Mono<TradingCompany> =
            this.httpClient.get()
                    .uri("/details/$ticker")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(TradingCompany::class.java)
                    .switchIfEmpty(Mono.error(TickerNotFoundException("Unknown Ticker: $ticker")))
}
