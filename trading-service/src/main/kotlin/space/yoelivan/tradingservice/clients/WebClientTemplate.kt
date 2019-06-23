package space.yoelivan.tradingservice.clients

import org.springframework.web.reactive.function.client.WebClient

abstract class WebClientTemplate(val url: String) {
    protected val httpClient = WebClient.create(url)
}
