package com.mr.questengine.listeners;

import com.mr.questengine.QuestEnginePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class QuestListener implements Listener {

    private final QuestEnginePlugin plugin;

    public QuestListener(QuestEnginePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            // 킬 퀘스트 진행 체크 (추후 구체적 목표 추적 구현)
            plugin.getLogger().fine(event.getEntity().getKiller().getName()
                    + " 처치: " + event.getEntity().getType().name());
        }
    }
}
