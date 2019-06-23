package space.yoelivan.stockdetails.repositories

import org.apache.logging.log4j.LogManager
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import space.yoelivan.stockdetails.domains.TradingCompany
import java.time.Duration

@Component
class TradingCompanyRepositoryInitializer(val repository: TradingCompanyRepository) : CommandLineRunner {
    companion object {
        private val log = LogManager.getLogger()
    }

    override fun run(vararg args: String?) {
        log.info("Initializing TradingCompanyRepository")
        val companies = Flux.just(
                TradingCompany("PVTL", "Pivotal Software"),
                TradingCompany("DELL", "Dell Technologies"),
                TradingCompany("GOOG", "Google"),
                TradingCompany("MSFT", "Microsoft"),
                TradingCompany("ORCL", "Oracle"),
                TradingCompany("RHT", "Red Hat"),
                TradingCompany("VMW", "Vmware")
        )
        repository.saveAll(companies)
                .blockLast(Duration.ofMinutes(1))
    }
}
