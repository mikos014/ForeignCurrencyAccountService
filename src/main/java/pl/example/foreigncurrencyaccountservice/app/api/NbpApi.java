package pl.example.foreigncurrencyaccountservice.app.api;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import pl.example.foreigncurrencyaccountservice.app.api.dto.ExchangeRateResponse;

@Service
public class NbpApi {
    private static final String BASE_URL = "https://api.nbp.pl/api";
    private final WebClient webClient;


    public NbpApi(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public ExchangeRateResponse getRateByCurrency(String currency) {
        final var path = String.format("/exchangerates/rates/c/%s", currency);

        return webClient.get()
                .uri(path)
                .header("format", "json")
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .block();
    }

}

