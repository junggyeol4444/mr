package com.mr.tutorial.models;

public enum TutorialStage {
    DIMENSION_ASSIGNED("차원 배정", "차원이 배정되었습니다."),
    COMBAT_BASICS("전투 기초", "전투 시스템을 체험하세요."),
    QUEST_BASICS("퀘스트 기초", "퀘스트 시스템을 체험하세요."),
    TRADE_BASICS("거래 기초", "거래 시스템을 체험하세요."),
    DEATH_BASICS("부활 시스템", "부활 시스템을 체험하세요."),
    COMPLETED("완료", "튜토리얼이 완료되었습니다.");

    private final String koreanName;
    private final String description;

    TutorialStage(String koreanName, String description) {
        this.koreanName = koreanName;
        this.description = description;
    }

    public String getKoreanName() { return koreanName; }
    public String getDescription() { return description; }

    public TutorialStage next() {
        TutorialStage[] vals = values();
        int idx = ordinal() + 1;
        return idx < vals.length ? vals[idx] : COMPLETED;
    }
}
