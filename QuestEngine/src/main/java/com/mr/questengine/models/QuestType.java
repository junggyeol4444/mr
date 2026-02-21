package com.mr.questengine.models;

public enum QuestType {
    PERSONAL("개인 퀘스트"),
    FREE("자유 퀘스트"),
    JOB_CLASS("직업/문파 퀘스트"),
    MAIN("메인 퀘스트"),
    GATE_EVENT("게이트 이벤트");

    private final String koreanName;

    QuestType(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
