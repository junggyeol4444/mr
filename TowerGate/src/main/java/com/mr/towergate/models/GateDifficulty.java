package com.mr.towergate.models;

public enum GateDifficulty {
    EASY("쉬움"),
    NORMAL("보통"),
    HARD("어려움"),
    EXTREME("극한");

    private final String koreanName;

    GateDifficulty(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
