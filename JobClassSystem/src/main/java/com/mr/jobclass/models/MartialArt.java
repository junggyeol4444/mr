package com.mr.jobclass.models;

public enum MartialArt {
    OUTER("외공"),
    INNER("내공"),
    MOVEMENT("경신"),
    MIND_METHOD("심법"),
    QINGGONG("경공");

    private final String koreanName;

    MartialArt(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
