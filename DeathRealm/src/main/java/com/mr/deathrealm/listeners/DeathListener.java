package com.mr.deathrealm.listeners;

import com.mr.deathrealm.DeathRealmPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final DeathRealmPlugin plugin;

    public DeathListener(DeathRealmPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // 명계 출생 영혼체 종족: 드롭 없음
        boolean noDrops = plugin.getConfig().getBoolean("death.spirit_body_drops_nothing", true);
        if (noDrops) {
            // BirthSystem 연동 시 종족 확인 후 드롭 제거
        }
        plugin.getDeathRealmManager().sendToNetherworld(event.getEntity());
    }
}
