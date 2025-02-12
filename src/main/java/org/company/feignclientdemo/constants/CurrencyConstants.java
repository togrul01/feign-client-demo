package org.company.feignclientdemo.constants;

import java.util.List;

public final class CurrencyConstants {
    public static final List<String> eligibleCurrencies = List.of("USD", "AZN", "EUR", "GBP");

    private CurrencyConstants() {
        // private constructor to prevent instantiation
    }
}
