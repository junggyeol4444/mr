package com.mr.deathrealm.models;

public enum DeathRealmArea {
    ROYAL_CITY("왕도"),
    CONTRACT_ROOM("계약의 방"),
    MARKET("시장"),
    CHECKPOINT("검문소");

    private final String koreanName;

    DeathRealmArea(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
