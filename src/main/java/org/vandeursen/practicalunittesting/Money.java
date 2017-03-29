package org.vandeursen.practicalunittesting;

/**
 * Created by jp on 26/02/2017.
 */
public class Money {
    private final int amount;
    private final String currency;
    public Money(int amount, String currency) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "illegal amount: [" + amount + "]");
        }
        if (currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException(
                    "illegal currency: [" + currency + "]");
        }
        this.amount = amount;
        this.currency = currency;
    }
    public int getAmount() {
        return amount;
    }
    // TODO: create new method


    public String getCurrency() {
        return currency;
    }
    public boolean equals(Object anObject) {
        if (anObject instanceof Money) {
            Money money = (Money) anObject;
            return money.getCurrency().equals(getCurrency())
                    && getAmount() == money.getAmount();
        }
        return false;
    }
}
