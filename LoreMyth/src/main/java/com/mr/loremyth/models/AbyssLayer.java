package com.mr.loremyth.models;

public enum AbyssLayer {
    UPPER("파멸의 왕들"),
    MIDDLE("타락한 영웅들"),
    LOWER("심연수와 암흑 결정체");

    private final String koreanName;

    AbyssLayer(String koreanName) { this.koreanName = koreanName; }
    public String getKoreanName() { return koreanName; }
}
