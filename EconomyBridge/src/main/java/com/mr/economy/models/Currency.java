package com.mr.economy.models;

public enum Currency {
    COPPER_COIN("동화"),
    SILVER_COIN("은화"),
    GOLD_COIN("금화"),
    PLATINUM_COIN("백금화"),
    COPPER_LIANG("동량"),
    SILVER_LIANG("은량"),
    GOLD_LIANG("금량"),
    COIN("코인"),
    SOUL("소울");

    private final String koreanName;

    Currency(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static Currency fromString(String name) {
        for (Currency c : values()) {
            if (c.name().equalsIgnoreCase(name) || c.koreanName.equals(name)) {
                return c;
            }
        }
        return null;
    }
}
