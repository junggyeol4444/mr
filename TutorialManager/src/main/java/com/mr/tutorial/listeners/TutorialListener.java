package com.mr.tutorial.listeners;

import com.mr.tutorial.TutorialManagerPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TutorialListener implements Listener {

    private final TutorialManagerPlugin plugin;

    public TutorialListener(TutorialManagerPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerFirstJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            plugin.getTutorialManager().startTutorial(event.getPlayer());
        }
    }
}
