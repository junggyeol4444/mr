package com.mr.npcengine.models;

public enum NPCRelation {
    ALLIED("동맹"),
    NEUTRAL("중립"),
    HOSTILE("적대");

    private final String koreanName;

    NPCRelation(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
