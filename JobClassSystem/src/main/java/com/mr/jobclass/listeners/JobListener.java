package com.mr.jobclass.listeners;

import com.mr.jobclass.JobClassPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JobListener implements Listener {

    private final JobClassPlugin plugin;

    public JobListener(JobClassPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // 직업 데이터 로드 (lazy init in manager)
        plugin.getLogger().fine(event.getPlayer().getName() + " 직업 시스템 로드됨");
    }
}
