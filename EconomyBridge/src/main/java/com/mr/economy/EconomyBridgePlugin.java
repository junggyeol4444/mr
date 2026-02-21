package com.mr.economy;

import com.mr.economy.commands.MoneyCommand;
import com.mr.economy.listeners.EconomyListener;
import com.mr.economy.managers.EconomyManager;
import com.mr.economy.managers.ExchangeManager;
import com.mr.economy.managers.SoulBankManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyBridgePlugin extends JavaPlugin {

    private static EconomyBridgePlugin instance;
    private EconomyManager economyManager;
    private ExchangeManager exchangeManager;
    private SoulBankManager soulBankManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        economyManager = new EconomyManager(this);
        exchangeManager = new ExchangeManager(this);
        soulBankManager = new SoulBankManager(this);
        getCommand("money").setExecutor(new MoneyCommand(this));
        getServer().getPluginManager().registerEvents(new EconomyListener(this), this);
        getLogger().info("EconomyBridge가 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("EconomyBridge가 비활성화되었습니다.");
    }

    public static EconomyBridgePlugin getInstance() { return instance; }
    public EconomyManager getEconomyManager() { return economyManager; }
    public ExchangeManager getExchangeManager() { return exchangeManager; }
    public SoulBankManager getSoulBankManager() { return soulBankManager; }
}
