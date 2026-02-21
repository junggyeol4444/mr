package com.mr.worldevents.models;

public enum WorldEvent {
    DEMON_EROSION_WEEK("마계 침식 주간", "판타지 대륙 디버프, 마물 및 악마 출현"),
    HEAVEN_RELIC_DESCENT("천계 성물강림", "타락한 성물 봉인 해제, 월드보스 출현"),
    DEMON_GATE_OPEN("요계 개문", "드롭률 상승, 요괴 토벌 및 포획 가능"),
    SYNC_GATE("싱크 게이트", "탑 50층 이상 등반 후 글로벌 이벤트 발동");

    private final String koreanName;
    private final String description;

    WorldEvent(String koreanName, String description) {
        this.koreanName = koreanName;
        this.description = description;
    }

    public String getKoreanName() { return koreanName; }
    public String getDescription() { return description; }
}
