package com.mr.questengine;

import com.mr.questengine.commands.QuestCommand;
import com.mr.questengine.listeners.QuestListener;
import com.mr.questengine.managers.QuestManager;
import org.bukkit.plugin.java.JavaPlugin;

public class QuestEnginePlugin extends JavaPlugin {

    private static QuestEnginePlugin instance;
    private QuestManager questManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        questManager = new QuestManager(this);
        getCommand("quest").setExecutor(new QuestCommand(this));
        getServer().getPluginManager().registerEvents(new QuestListener(this), this);
        getLogger().info("QuestEngine이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("QuestEngine이 비활성화되었습니다.");
    }

    public static QuestEnginePlugin getInstance() {
        return instance;
    }

    public QuestManager getQuestManager() {
        return questManager;
    }
}
