package com.mr.dimensioncore.models;

public enum Dimension {
    FANTASY("판타지"),
    MARTIAL("무협"),
    EARTH("지구"),
    UNDERWORLD("마계"),
    HEAVEN("천계"),
    IMMORTAL("선계"),
    DEMON("요계"),
    SPIRIT("정령계"),
    NETHERWORLD("명계"),
    TOWER("탑");

    private final String koreanName;

    Dimension(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static Dimension fromString(String name) {
        for (Dimension d : values()) {
            if (d.name().equalsIgnoreCase(name) || d.koreanName.equals(name)) {
                return d;
            }
        }
        return null;
    }
}
