package com.mr.npcengine.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NPC {

    private final String id;
    private final String name;
    private final String dimension;
    private final Map<UUID, Integer> affinityMap = new HashMap<>();
    private boolean isAlive;
    private boolean isInNetherworld;

    public NPC(String id, String name, String dimension) {
        this.id = id;
        this.name = name;
        this.dimension = dimension;
        this.isAlive = true;
        this.isInNetherworld = false;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDimension() { return dimension; }
    public boolean isAlive() { return isAlive; }
    public boolean isInNetherworld() { return isInNetherworld; }

    public int getAffinity(UUID playerUUID) {
        return affinityMap.getOrDefault(playerUUID, 0);
    }

    public void adjustAffinity(UUID playerUUID, int delta) {
        int current = getAffinity(playerUUID);
        affinityMap.put(playerUUID, Math.max(-100, Math.min(100, current + delta)));
    }

    public void die() {
        isAlive = false;
        isInNetherworld = true;
    }

    public void resurrect() {
        isAlive = true;
        isInNetherworld = false;
    }
}
