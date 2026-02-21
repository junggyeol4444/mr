package com.mr.statsystem.models;

public enum StatType {
    STR("근력"),
    DEX("민첩"),
    INT("지능"),
    VIT("체력"),
    WIS("지혜"),
    CHA("매력"),
    LUK("행운"),
    RES("저항"),
    MAGIC_ENERGY("마기"),
    HEAVEN_ENERGY("천기"),
    IMMORTAL_ENERGY("선기"),
    DEMON_ENERGY("요기"),
    SOUL_ENERGY("혼기"),
    DARK_MANA("어둠 마나"),
    SPIRIT_MANA("정령 마나"),
    JIN_GI("진기"),
    RUNE_SLOT("룬 슬롯"),
    SPIRIT_SLOT("정령 슬롯");

    private final String koreanName;

    StatType(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static StatType fromString(String name) {
        for (StatType s : values()) {
            if (s.name().equalsIgnoreCase(name) || s.koreanName.equals(name)) {
                return s;
            }
        }
        return null;
    }
}
