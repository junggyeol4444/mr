package com.mr.raceevolution.models;

public class TranscendenceStatus {

    private boolean transcended;
    private int transcendenceLevel;

    public TranscendenceStatus() {
        this.transcended = false;
        this.transcendenceLevel = 0;
    }

    public boolean isTranscended() {
        return transcended;
    }

    public int getTranscendenceLevel() {
        return transcendenceLevel;
    }

    public void transcend() {
        this.transcended = true;
        this.transcendenceLevel++;
    }
}
