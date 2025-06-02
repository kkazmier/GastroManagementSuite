package pl.gastro.gastro_management_suite.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateService {
    private final RestTemplate restTemplate = new RestTemplate();

    public BigDecimal getExchangeRate(String currency) {
        String url = "https://api.nbp.pl/api/exchangerates/rates/A/" + currency + "/?format=json";
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> rates = (List<Map<String, Object>>) response.get("rates");
            return new BigDecimal(rates.get(0).get("mid").toString());
        } catch (Exception e) {
            throw new RuntimeException("Nie można pobrać kursu waluty: " + currency);
        }
    }
}

