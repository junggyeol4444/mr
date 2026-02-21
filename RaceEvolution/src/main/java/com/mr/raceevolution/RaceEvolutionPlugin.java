package com.mr.raceevolution;

import com.mr.raceevolution.commands.EvolveCommand;
import com.mr.raceevolution.listeners.EvolutionListener;
import com.mr.raceevolution.managers.EvolutionManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RaceEvolutionPlugin extends JavaPlugin {

    private static RaceEvolutionPlugin instance;
    private EvolutionManager evolutionManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        evolutionManager = new EvolutionManager(this);
        EvolveCommand cmd = new EvolveCommand(this);
        getCommand("evolve").setExecutor(cmd);
        getCommand("transcend").setExecutor(cmd);
        getServer().getPluginManager().registerEvents(new EvolutionListener(this), this);
        getLogger().info("RaceEvolution이 활성화되었습니다.");
    }

    @Override
    public void onDisable() { getLogger().info("RaceEvolution이 비활성화되었습니다."); }

    public static RaceEvolutionPlugin getInstance() { return instance; }
    public EvolutionManager getEvolutionManager() { return evolutionManager; }
}
