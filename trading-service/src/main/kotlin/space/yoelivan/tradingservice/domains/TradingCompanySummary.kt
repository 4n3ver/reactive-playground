package space.yoelivan.tradingservice.domains

data class TradingCompanySummary(
        val latestQuote: Quote,
        val tradingCompany: TradingCompany
)
