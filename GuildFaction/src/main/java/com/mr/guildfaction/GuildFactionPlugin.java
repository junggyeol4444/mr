package com.mr.guildfaction;

import com.mr.guildfaction.commands.GuildCommand;
import com.mr.guildfaction.listeners.GuildListener;
import com.mr.guildfaction.managers.GuildManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GuildFactionPlugin extends JavaPlugin {

    private static GuildFactionPlugin instance;
    private GuildManager guildManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        guildManager = new GuildManager(this);
        getCommand("guild").setExecutor(new GuildCommand(this));
        getServer().getPluginManager().registerEvents(new GuildListener(this), this);
        getLogger().info("GuildFaction이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        getLogger().info("GuildFaction이 비활성화되었습니다.");
    }

    public static GuildFactionPlugin getInstance() { return instance; }
    public GuildManager getGuildManager() { return guildManager; }
}
