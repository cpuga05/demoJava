package shop.domain.model.shared;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Money {
    private double amount;
    private Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money fromString(String money) throws InvalidArgumentException {
        Pattern pattern = Pattern.compile("^([-]?[0-9]+(.[0-9]+)?)[ ]?([A-Z]{3})$");
        Matcher matcher = pattern.matcher(money);

        if (!matcher.matches()) {
            throw new InvalidArgumentException(new String[]{"Not valid money from string."});
        }

        return new Money(Double.valueOf(matcher.group(1)), new Currency(matcher.group(3)));
    }

    public boolean equals(Money money) {
        return this.currency().equals(money.currency()) && this.amount() == money.amount();
    }

    public Money add(Money money) throws InvalidArgumentException {
        if (!this.currency().equals(money.currency())) {
            throw new InvalidArgumentException(new String[]{"Not same currency"});
        }

        return new Money(this.amount() + money.amount(), new Currency(this.currency().isoCode()));
    }

    public Money multiply(double value) throws InvalidArgumentException {
        return new Money(this.amount() * value, new Currency(this.currency().isoCode()));
    }

    public double amount() {
        return this.amount;
    }

    public Currency currency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return this.amount() + " " + this.currency().isoCode();
    }
}
