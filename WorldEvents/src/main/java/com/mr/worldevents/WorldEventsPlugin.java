package com.mr.worldevents;

import com.mr.worldevents.listeners.EventListener;
import com.mr.worldevents.managers.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldEventsPlugin extends JavaPlugin {

    private static WorldEventsPlugin instance;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        eventManager = new EventManager(this);
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getLogger().info("WorldEvents가 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("WorldEvents가 비활성화되었습니다.");
    }

    public static WorldEventsPlugin getInstance() { return instance; }
    public EventManager getEventManager() { return eventManager; }
}
