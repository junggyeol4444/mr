package com.mr.loremyth.models;

public enum AbyssFaction {
    GUARDIAN("수호자"),
    CORRUPT("타락자"),
    ESCAPEE("탈출자");

    private final String koreanName;

    AbyssFaction(String koreanName) { this.koreanName = koreanName; }
    public String getKoreanName() { return koreanName; }
}
