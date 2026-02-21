package com.mr.birthsystem;

import com.mr.birthsystem.listeners.BirthListener;
import com.mr.birthsystem.managers.BirthManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BirthSystemPlugin extends JavaPlugin {

    private static BirthSystemPlugin instance;
    private BirthManager birthManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        birthManager = new BirthManager(this);
        getServer().getPluginManager().registerEvents(new BirthListener(this), this);
        getLogger().info("BirthSystem이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("BirthSystem이 비활성화되었습니다.");
    }

    public static BirthSystemPlugin getInstance() {
        return instance;
    }

    public BirthManager getBirthManager() {
        return birthManager;
    }
}
