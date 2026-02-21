package com.mr.tutorial;

import com.mr.tutorial.listeners.TutorialListener;
import com.mr.tutorial.managers.TutorialManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TutorialManagerPlugin extends JavaPlugin {

    private static TutorialManagerPlugin instance;
    private TutorialManager tutorialManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        tutorialManager = new TutorialManager(this);
        getServer().getPluginManager().registerEvents(new TutorialListener(this), this);
        getLogger().info("TutorialManager가 활성화되었습니다.");
    }

    @Override
    public void onDisable() { getLogger().info("TutorialManager가 비활성화되었습니다."); }

    public static TutorialManagerPlugin getInstance() { return instance; }
    public TutorialManager getTutorialManager() { return tutorialManager; }
}
