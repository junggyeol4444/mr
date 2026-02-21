package com.mr.economy.listeners;

import com.mr.economy.EconomyBridgePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EconomyListener implements Listener {

    private final EconomyBridgePlugin plugin;

    public EconomyListener(EconomyBridgePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // 플레이어 초기 잔액 설정 (lazy init in manager)
        plugin.getEconomyManager().getAllBalances(event.getPlayer());
    }
}
