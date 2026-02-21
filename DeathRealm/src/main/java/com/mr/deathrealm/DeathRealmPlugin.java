package com.mr.deathrealm;

import com.mr.deathrealm.commands.ResurrectCommand;
import com.mr.deathrealm.listeners.DeathListener;
import com.mr.deathrealm.managers.DeathRealmManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathRealmPlugin extends JavaPlugin {

    private static DeathRealmPlugin instance;
    private DeathRealmManager deathRealmManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        deathRealmManager = new DeathRealmManager(this);
        getCommand("resurrect").setExecutor(new ResurrectCommand(this));
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getLogger().info("DeathRealm이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DeathRealm이 비활성화되었습니다.");
    }

    public static DeathRealmPlugin getInstance() { return instance; }
    public DeathRealmManager getDeathRealmManager() { return deathRealmManager; }
}
