package shop.domain.model.shared;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;

public final class Currency {
    private static final String[] VALID_CURRENCIES = {"EUR", "USD", "JPY", "BGN", "CZK", "DKK", "GBP", "HUF", "PLN", "RON", "SEK", "CHF", "ISK", "NOK", "HRK", "RUB", "TRY", "AUD", "BRL", "CAD", "CNY", "HKD", "IDR", "ILS", "INR", "KRW", "MXN", "MYR", "NZD", "PHP", "SGD", "THB", "ZAR"};
    private String isoCode;

    public Currency(String isoCode) throws InvalidArgumentException {
        isoCode = isoCode.toUpperCase();

        this.ensureValidCurrency(isoCode);

        this.isoCode = isoCode;
    }

    private void ensureValidCurrency(String isoCode) throws InvalidArgumentException {
        if (!Arrays.asList(Currency.VALID_CURRENCIES).contains(isoCode)) {
            throw new InvalidArgumentException(new String[]{"Te currency " + isoCode + " not is valid."});
        }
    }

    public boolean equals(Currency currency) {
        return this.isoCode().equals(currency.isoCode());
    }

    public String isoCode() {
        return this.isoCode;
    }
}
