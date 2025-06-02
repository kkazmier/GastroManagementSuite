package pl.gastro.gastro_management_suite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gastro.gastro_management_suite.service.ExchangeRateService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @GetMapping("/{currency}")
    public BigDecimal getRate(@PathVariable String currency) {
        return exchangeRateService.getExchangeRate(currency);
    }
}

