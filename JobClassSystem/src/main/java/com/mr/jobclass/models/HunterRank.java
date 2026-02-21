package com.mr.jobclass.models;

public enum HunterRank {
    E("E"),
    D("D"),
    C("C"),
    B("B"),
    A("A"),
    S("S"),
    SS("SS"),
    SSS("SSS");

    private final String displayName;

    HunterRank(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public HunterRank next() {
        HunterRank[] vals = values();
        int idx = ordinal() + 1;
        return idx < vals.length ? vals[idx] : this;
    }
}
