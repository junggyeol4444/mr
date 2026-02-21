package com.mr.jobclass.models;

public enum AwakeningType {
    SHADOW("섀도우"),
    TELEKINESIS("염동력"),
    REINFORCEMENT("강화"),
    SUMMON("소환"),
    GRAVITY("중력"),
    ELECTROMAGNETIC("전자기"),
    TIME_WARP("시간왜곡"),
    BIO_MODIFICATION("생체개조"),
    DATA_HACKING("데이터해킹");

    private final String koreanName;

    AwakeningType(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
