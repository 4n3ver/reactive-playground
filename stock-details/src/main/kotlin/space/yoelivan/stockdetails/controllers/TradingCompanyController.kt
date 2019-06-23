package space.yoelivan.stockdetails.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import space.yoelivan.stockdetails.domains.TradingCompany
import space.yoelivan.stockdetails.repositories.TradingCompanyRepository

@RestController
@RequestMapping("/companies")
class TradingCompanyController(
        val tradingCompanyRepository: TradingCompanyRepository
) {
    @GetMapping(path = ["/details"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun companiesDetails(): Flux<TradingCompany> {
        return tradingCompanyRepository.findAll()
    }

    @GetMapping(path = ["/details/{ticker}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun companyDetail(@PathVariable ticker: String): Mono<TradingCompany> {
        return tradingCompanyRepository.findByTicker(ticker)
    }
}
