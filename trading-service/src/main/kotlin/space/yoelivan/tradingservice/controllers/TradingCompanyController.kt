package space.yoelivan.tradingservice.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import space.yoelivan.tradingservice.clients.TradingCompanyClient
import space.yoelivan.tradingservice.domains.TradingCompany

@RestController
@RequestMapping("/details")
class TradingCompanyController(val tradingCompanyClient: TradingCompanyClient) {
    @GetMapping(produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun details(): Flux<TradingCompany> =
            tradingCompanyClient.getAllCompanies()

    @GetMapping(path = ["/{ticker}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun details(@PathVariable ticker: String): Mono<TradingCompany> =
            tradingCompanyClient.getCompany(ticker)
}
