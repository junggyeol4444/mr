package com.mr.raceevolution.managers;

import com.mr.raceevolution.RaceEvolutionPlugin;
import com.mr.raceevolution.models.EvolutionPath;
import com.mr.raceevolution.models.TranscendenceStatus;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EvolutionManager {

    private final RaceEvolutionPlugin plugin;
    private final Map<UUID, EvolutionPath> evolvedPlayers = new HashMap<>();
    private final Map<UUID, TranscendenceStatus> transcendenceMap = new HashMap<>();

    public EvolutionManager(RaceEvolutionPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean hasEvolved(Player player) {
        return evolvedPlayers.containsKey(player.getUniqueId());
    }

    public EvolutionPath getEvolutionPath(Player player) {
        return evolvedPlayers.get(player.getUniqueId());
    }

    public boolean evolve(Player player, EvolutionPath path) {
        if (hasEvolved(player)) {
            player.sendMessage(plugin.getConfig().getString("messages.evolve_already",
                    "§c이미 진화한 종족입니다."));
            return false;
        }
        // 조건 확인 (실제 구현 시 StatSystem 연동)
        evolvedPlayers.put(player.getUniqueId(), path);
        player.sendMessage(plugin.getConfig().getString("messages.evolve_success",
                "§a§l종족이 진화했습니다! → §e%race%")
                .replace("%race%", path.getKoreanName()));
        plugin.getLogger().info(player.getName() + " 종족 진화: " + path.getKoreanName());
        return true;
    }

    public boolean transcend(Player player) {
        TranscendenceStatus status = transcendenceMap.computeIfAbsent(
                player.getUniqueId(), k -> new TranscendenceStatus());
        int maxLevel = plugin.getConfig().getInt("transcendence.max_level", 3);
        if (status.getTranscendenceLevel() >= maxLevel) {
            player.sendMessage(plugin.getConfig().getString("messages.transcend_already_max",
                    "§c이미 최대 초월 단계입니다."));
            return false;
        }
        status.transcend();
        player.sendMessage(plugin.getConfig().getString("messages.transcend_success",
                "§6§l종족 초월! 신적 존재로 승격되었습니다! (초월 레벨: %level%)")
                .replace("%level%", String.valueOf(status.getTranscendenceLevel())));
        return true;
    }

    public TranscendenceStatus getTranscendenceStatus(Player player) {
        return transcendenceMap.computeIfAbsent(player.getUniqueId(), k -> new TranscendenceStatus());
    }
}
