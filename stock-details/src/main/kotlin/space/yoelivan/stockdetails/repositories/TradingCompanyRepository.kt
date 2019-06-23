package space.yoelivan.stockdetails.repositories

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono
import space.yoelivan.stockdetails.domains.TradingCompany

interface TradingCompanyRepository: ReactiveMongoRepository<TradingCompany, String> {
    fun findByTicker(ticker: String): Mono<TradingCompany>
}
