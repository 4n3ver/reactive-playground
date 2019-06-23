package space.yoelivan.stockquotes.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import space.yoelivan.stockquotes.domains.Quote
import space.yoelivan.stockquotes.services.QuoteService
import java.time.Duration

@RestController
@RequestMapping("/quotes")
class QuotesController(val quoteService: QuoteService) {
    @GetMapping(path=["/feed"], produces=[MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun quotesFeed(): Flux<Quote> =
            quoteService.fetchQuote(Duration.ofSeconds(1))

}
