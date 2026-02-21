package com.mr.guildfaction.models;

import java.util.*;

public class Guild {

    private final String id;
    private String name;
    private UUID leader;
    private final Map<UUID, GuildRank> members = new LinkedHashMap<>();
    private String territory;
    private double taxRate;

    public Guild(String id, String name, UUID leader) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.taxRate = 0.05;
        members.put(leader, GuildRank.LEADER);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public UUID getLeader() { return leader; }
    public String getTerritory() { return territory; }
    public double getTaxRate() { return taxRate; }
    public Map<UUID, GuildRank> getMembers() { return Collections.unmodifiableMap(members); }
    public int getMemberCount() { return members.size(); }

    public void addMember(UUID uuid) { members.put(uuid, GuildRank.MEMBER); }
    public void removeMember(UUID uuid) { members.remove(uuid); }
    public boolean hasMember(UUID uuid) { return members.containsKey(uuid); }
    public GuildRank getMemberRank(UUID uuid) { return members.getOrDefault(uuid, GuildRank.MEMBER); }

    public void setTerritory(String territory) { this.territory = territory; }
    public void setTaxRate(double rate) { this.taxRate = Math.max(0.01, Math.min(0.20, rate)); }
}
