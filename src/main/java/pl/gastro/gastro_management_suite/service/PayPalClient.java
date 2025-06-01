package pl.gastro.gastro_management_suite.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class PayPalClient {

    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.secret}")
    private String secret;

    @Value("${paypal.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken() {
        // wysyłka POST na /v1/oauth2/token z client_id:secret zakodowanym w Basic Auth
        // response: access_token
        return null;
    }

    public String createPayPalOrder(BigDecimal amount) {
        // wysyłka POST na /v2/checkout/orders
        // response: id + link do autoryzacji
        return null;
    }
}
