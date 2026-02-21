package com.mr.raceevolution.models;

public enum EvolutionPath {
    SPIRIT_HUMAN("정령인"),
    HALF_DEMON("반마족"),
    MECHANICAL_HUMAN("기계인"),
    CORRUPTION("타락자");

    private final String koreanName;

    EvolutionPath(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static EvolutionPath fromString(String name) {
        for (EvolutionPath p : values()) {
            if (p.name().equalsIgnoreCase(name) || p.koreanName.equals(name)) {
                return p;
            }
        }
        return null;
    }
}
