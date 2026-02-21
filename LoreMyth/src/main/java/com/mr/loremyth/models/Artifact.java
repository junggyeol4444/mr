package com.mr.loremyth.models;

public enum Artifact {
    FLAME_OF_LUMEN("루멘의 불씨", "fantasy"),
    CORE_OF_TAICHI("태극의 핵", "martial"),
    PROTOSS_CUBE("프로토스 큐브", "earth");

    private final String koreanName;
    private final String dimension;

    Artifact(String koreanName, String dimension) {
        this.koreanName = koreanName;
        this.dimension = dimension;
    }

    public String getKoreanName() { return koreanName; }
    public String getDimension() { return dimension; }
}
