package com.mr.guildfaction.models;

public enum GuildRank {
    MEMBER("단원"),
    OFFICER("간부"),
    VICE_LEADER("부길드장"),
    LEADER("길드장");

    private final String koreanName;

    GuildRank(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
