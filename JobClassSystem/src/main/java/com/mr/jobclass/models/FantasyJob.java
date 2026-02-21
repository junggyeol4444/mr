package com.mr.jobclass.models;

public enum FantasyJob {
    SWORD_WARRIOR("검전사"),
    HOLY_KNIGHT("성기사"),
    MAGE("마법사"),
    RUNE_ENGINEER("룬공학자"),
    SPIRIT_SHAMAN("정령주술사"),
    BARD("음유시인"),
    ARCANE_ARCHER("아케인아처"),
    ARMORED_LANCER("기갑창병"),
    ASTROLOGER("점성술사"),
    BLOOD_SPIRIT("혈귀술사"),
    DRUID("드루이드"),
    NECROMANCER("네크로맨서"),
    ELEMENTALIST("원소술사"),
    ALCHEMIST("연금술사"),
    ARCANE_SCHOLAR("비전학자"),
    ASSASSIN("암살자"),
    MONK("수도사");

    private final String koreanName;

    FantasyJob(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static FantasyJob fromString(String name) {
        for (FantasyJob j : values()) {
            if (j.name().equalsIgnoreCase(name) || j.koreanName.equals(name)) {
                return j;
            }
        }
        return null;
    }
}
