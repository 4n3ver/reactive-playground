package space.yoelivan.tradingservice.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import space.yoelivan.tradingservice.domains.Quote
import space.yoelivan.tradingservice.domains.TradingCompanySummary
import space.yoelivan.tradingservice.exceptions.TickerNotFoundException
import space.yoelivan.tradingservice.services.TradingService


@RestController
@RequestMapping("/quotes")
class QuotesController(val tradingService: TradingService) {
    @GetMapping(path = ["/feed"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun details(): Flux<Quote> = tradingService.getQuotesFeed()

    @GetMapping(path = ["/summary/{ticker}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun details(@PathVariable ticker: String): Mono<TradingCompanySummary> =
            tradingService.getQuoteDetails(ticker)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TickerNotFoundException::class)
    fun onTickerNotFound() {
    }
}
