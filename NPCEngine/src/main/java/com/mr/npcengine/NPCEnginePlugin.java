package com.mr.npcengine;

import com.mr.npcengine.listeners.NPCListener;
import com.mr.npcengine.managers.NPCManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NPCEnginePlugin extends JavaPlugin {

    private static NPCEnginePlugin instance;
    private NPCManager npcManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        npcManager = new NPCManager(this);
        getServer().getPluginManager().registerEvents(new NPCListener(this), this);
        getLogger().info("NPCEngine이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NPCEngine이 비활성화되었습니다.");
    }

    public static NPCEnginePlugin getInstance() { return instance; }
    public NPCManager getNPCManager() { return npcManager; }
}
