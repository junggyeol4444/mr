package com.mr.birthsystem.models;

public enum Race {
    HUMAN("인간"),
    DEMON("악마"),
    ANGEL("천사"),
    IMMORTAL("선인"),
    YOKAI("요괴"),
    SPIRIT_BODY("영혼체"),
    SPIRIT_KIN("정령인");

    private final String koreanName;

    Race(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
