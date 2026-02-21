package com.mr.towergate;

import com.mr.towergate.commands.TowerCommand;
import com.mr.towergate.listeners.TowerListener;
import com.mr.towergate.managers.TowerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TowerGatePlugin extends JavaPlugin {

    private static TowerGatePlugin instance;
    private TowerManager towerManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        towerManager = new TowerManager(this);
        getCommand("tower").setExecutor(new TowerCommand(this));
        getServer().getPluginManager().registerEvents(new TowerListener(this), this);
        getLogger().info("TowerGate가 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("TowerGate가 비활성화되었습니다.");
    }

    public static TowerGatePlugin getInstance() { return instance; }
    public TowerManager getTowerManager() { return towerManager; }
}
