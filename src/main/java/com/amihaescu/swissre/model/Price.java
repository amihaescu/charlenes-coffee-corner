package com.amihaescu.swissre.model;

public final class Price {

    private final float amount;
    private final String currency;

    public Price(float amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return amount + currency;
    }
}
