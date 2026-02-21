package com.mr.questengine.models;

import java.util.List;

public class Quest {

    private final String id;
    private final String name;
    private final QuestType type;
    private final String description;
    private final List<String> objectives;
    private final long expReward;

    public Quest(String id, String name, QuestType type, String description,
                 List<String> objectives, long expReward) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.objectives = objectives;
        this.expReward = expReward;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public QuestType getType() { return type; }
    public String getDescription() { return description; }
    public List<String> getObjectives() { return objectives; }
    public long getExpReward() { return expReward; }
}
