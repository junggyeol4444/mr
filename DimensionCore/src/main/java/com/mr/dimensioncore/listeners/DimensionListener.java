package com.mr.dimensioncore.listeners;

import com.mr.dimensioncore.DimensionCorePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DimensionListener implements Listener {

    private final DimensionCorePlugin plugin;

    public DimensionListener(DimensionCorePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " 차원 코어 로드됨");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // 추후 플레이어 차원 데이터 저장
    }
}
