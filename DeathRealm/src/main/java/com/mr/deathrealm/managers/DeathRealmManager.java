package com.mr.deathrealm.managers;

import com.mr.deathrealm.DeathRealmPlugin;
import org.bukkit.entity.Player;

import java.util.*;

public class DeathRealmManager {

    private final DeathRealmPlugin plugin;
    private final Set<UUID> deadPlayers = new HashSet<>();
    private final Map<UUID, Long> prepaidResurrection = new HashMap<>();
    private long currentResurrectionCost;

    public DeathRealmManager(DeathRealmPlugin plugin) {
        this.plugin = plugin;
        this.currentResurrectionCost = plugin.getConfig().getLong("resurrection.base_cost", 50000L);
    }

    public boolean isDead(Player player) {
        return deadPlayers.contains(player.getUniqueId());
    }

    public void sendToNetherworld(Player player) {
        deadPlayers.add(player.getUniqueId());
        player.sendMessage(plugin.getConfig().getString("messages.death_sent_to_netherworld",
                "§c당신이 사망했습니다. 명계로 이동합니다..."));
        player.sendMessage(plugin.getConfig().getString("messages.resurrect_quest_option",
                "§b부활 퀘스트를 완료하거나 §e%cost% 소울§b을 지불해 부활할 수 있습니다.")
                .replace("%cost%", String.valueOf(currentResurrectionCost)));
    }

    public boolean prepay(Player player, long amount) {
        prepaidResurrection.put(player.getUniqueId(),
                prepaidResurrection.getOrDefault(player.getUniqueId(), 0L) + amount);
        return true;
    }

    public long getPrepaid(Player player) {
        return prepaidResurrection.getOrDefault(player.getUniqueId(), 0L);
    }

    public boolean resurrect(Player player, boolean useBank) {
        if (!isDead(player)) return false;
        if (useBank) {
            long prepaid = getPrepaid(player);
            if (prepaid >= currentResurrectionCost) {
                prepaidResurrection.put(player.getUniqueId(), prepaid - currentResurrectionCost);
                deadPlayers.remove(player.getUniqueId());
                player.sendMessage(plugin.getConfig().getString("messages.resurrect_success",
                        "§a부활에 성공했습니다!"));
                return true;
            }
        }
        return false;
    }

    public void increaseResurrectionCost(long amount) {
        currentResurrectionCost += amount;
    }

    public long getCurrentResurrectionCost() {
        return currentResurrectionCost;
    }
}
