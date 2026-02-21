package com.mr.towergate.models;

public class TowerFloor {

    private final int floorNumber;
    private final String theme;
    private final boolean timeLimited;
    private final boolean forbidden;
    private final boolean nationCoopRequired;

    public TowerFloor(int floorNumber, String theme, boolean timeLimited,
                      boolean forbidden, boolean nationCoopRequired) {
        this.floorNumber = floorNumber;
        this.theme = theme;
        this.timeLimited = timeLimited;
        this.forbidden = forbidden;
        this.nationCoopRequired = nationCoopRequired;
    }

    public int getFloorNumber() { return floorNumber; }
    public String getTheme() { return theme; }
    public boolean isTimeLimited() { return timeLimited; }
    public boolean isForbidden() { return forbidden; }
    public boolean isNationCoopRequired() { return nationCoopRequired; }
}
