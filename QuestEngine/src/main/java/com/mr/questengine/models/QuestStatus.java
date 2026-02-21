package com.mr.questengine.models;

public enum QuestStatus {
    NOT_STARTED("시작 전"),
    IN_PROGRESS("진행 중"),
    COMPLETED("완료"),
    FAILED("실패");

    private final String koreanName;

    QuestStatus(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
