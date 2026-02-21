package com.mr.statsystem;

import com.mr.statsystem.commands.StatPointCommand;
import com.mr.statsystem.commands.StatsCommand;
import com.mr.statsystem.listeners.StatListener;
import com.mr.statsystem.managers.StatManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StatSystemPlugin extends JavaPlugin {

    private static StatSystemPlugin instance;
    private StatManager statManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        statManager = new StatManager(this);
        getCommand("stats").setExecutor(new StatsCommand(this));
        getCommand("statpoint").setExecutor(new StatPointCommand(this));
        getServer().getPluginManager().registerEvents(new StatListener(this), this);
        getLogger().info("StatSystem이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("StatSystem이 비활성화되었습니다.");
    }

    public static StatSystemPlugin getInstance() {
        return instance;
    }

    public StatManager getStatManager() {
        return statManager;
    }
}
