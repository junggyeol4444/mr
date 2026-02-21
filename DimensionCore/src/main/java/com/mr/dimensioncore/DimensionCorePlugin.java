package com.mr.dimensioncore;

import com.mr.dimensioncore.api.DimensionAPI;
import com.mr.dimensioncore.commands.WorldSwitchCommand;
import com.mr.dimensioncore.listeners.DimensionListener;
import com.mr.dimensioncore.managers.BalanceManager;
import com.mr.dimensioncore.managers.DimensionManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class DimensionCorePlugin extends JavaPlugin {

    private static DimensionCorePlugin instance;
    private DimensionManager dimensionManager;
    private BalanceManager balanceManager;
    private DimensionAPI dimensionAPI;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        dimensionManager = new DimensionManager(this);
        balanceManager = new BalanceManager(this);
        dimensionAPI = new DimensionAPI(this);

        getServer().getServicesManager().register(
                DimensionAPI.class, dimensionAPI, this, ServicePriority.Normal);

        getCommand("worldswitch").setExecutor(new WorldSwitchCommand(this));
        getServer().getPluginManager().registerEvents(new DimensionListener(this), this);

        balanceManager.startBalanceTick();
        getLogger().info("DimensionCore가 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        if (balanceManager != null) {
            balanceManager.stopBalanceTick();
        }
        getLogger().info("DimensionCore가 비활성화되었습니다.");
    }

    public static DimensionCorePlugin getInstance() {
        return instance;
    }

    public DimensionManager getDimensionManager() {
        return dimensionManager;
    }

    public BalanceManager getBalanceManager() {
        return balanceManager;
    }

    public DimensionAPI getDimensionAPI() {
        return dimensionAPI;
    }
}
