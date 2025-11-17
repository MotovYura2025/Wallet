package com.example.walletapi.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class CurrencyConversionService {

    private final WebClient webClient;
    private final Cache<String, Map<String, Double>> priceCache;

    public CurrencyConversionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.coingecko.com/api/v3").build();
        this.priceCache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build();
    }

    public double convertCryptoToFiat(String cryptoId, String fiatCurrency, double amount) {
        Map<String, Double> prices = getPricesFromCache(cryptoId.toLowerCase(), fiatCurrency.toLowerCase());
        Double rate = prices.get(fiatCurrency.toLowerCase());
        if (rate == null) {
            throw new RuntimeException("Курс для " + cryptoId + " -> " + fiatCurrency + " не найден");
        }
        return rate * amount;
    }

    private Map<String, Double> getPricesFromCache(String cryptoId, String fiatCurrency) {
        String cacheKey = cryptoId + ":" + fiatCurrency;
        try {
            return priceCache.get(cacheKey, () -> fetchPrices(cryptoId, fiatCurrency));
        } catch (ExecutionException e) {
            throw new RuntimeException("Ошибка получения курса", e);
        }
    }

    private Map<String, Double> fetchPrices(String cryptoId, String fiatCurrency) {
        String uri = "/simple/price?ids=" + cryptoId + "&vs_currencies=" + fiatCurrency;
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Map<String, Double>>>() {})
                .map(response -> response.getOrDefault(cryptoId, Map.of()))
                .block();
    }
}
