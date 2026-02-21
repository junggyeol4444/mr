package com.mr.loremyth;

import com.mr.loremyth.listeners.LoreListener;
import com.mr.loremyth.managers.LoreManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LoreMythPlugin extends JavaPlugin {

    private static LoreMythPlugin instance;
    private LoreManager loreManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        loreManager = new LoreManager(this);
        getServer().getPluginManager().registerEvents(new LoreListener(this), this);
        getLogger().info("LoreMyth가 활성화되었습니다.");
    }

    @Override
    public void onDisable() { getLogger().info("LoreMyth가 비활성화되었습니다."); }

    public static LoreMythPlugin getInstance() { return instance; }
    public LoreManager getLoreManager() { return loreManager; }
}
