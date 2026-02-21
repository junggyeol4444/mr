package com.mr.birthsystem.listeners;

import com.mr.birthsystem.BirthSystemPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BirthListener implements Listener {

    private final BirthSystemPlugin plugin;

    public BirthListener(BirthSystemPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerFirstJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            plugin.getBirthManager().assignBirthDimension(event.getPlayer());
        }
    }
}
