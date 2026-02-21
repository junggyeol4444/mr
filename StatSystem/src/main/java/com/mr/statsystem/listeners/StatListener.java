package com.mr.statsystem.listeners;

import com.mr.statsystem.StatSystemPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class StatListener implements Listener {

    private final StatSystemPlugin plugin;

    public StatListener(StatSystemPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // 플레이어 스탯 초기화 (lazy init in manager)
        plugin.getStatManager().getLevel(event.getPlayer());
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            int expGain = (int) (event.getEntity().getMaxHealth() * 2);
            plugin.getStatManager().addExp(event.getEntity().getKiller(), expGain);
        }
    }
}
