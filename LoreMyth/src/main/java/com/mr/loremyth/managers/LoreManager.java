package com.mr.loremyth.managers;

import com.mr.loremyth.LoreMythPlugin;
import com.mr.loremyth.models.Artifact;
import com.mr.loremyth.models.AbyssFaction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class LoreManager {

    private final LoreMythPlugin plugin;
    private final Set<Artifact> collectedArtifacts = EnumSet.noneOf(Artifact.class);
    private final Map<UUID, AbyssFaction> playerFactions = new HashMap<>();
    private boolean abyssOpened = false;

    public LoreManager(LoreMythPlugin plugin) {
        this.plugin = plugin;
    }

    public void collectArtifact(Player player, Artifact artifact) {
        collectedArtifacts.add(artifact);
        String msg = plugin.getConfig().getString("messages.artifact_found", "§6§l[신화] §e%artifact%§6을(를) 발견했습니다!")
                .replace("%artifact%", artifact.getKoreanName());
        Bukkit.broadcastMessage(msg);
        checkAbyssCondition();
    }

    private void checkAbyssCondition() {
        if (!abyssOpened && collectedArtifacts.containsAll(Arrays.asList(Artifact.values()))) {
            openAbyss();
        }
    }

    private void openAbyss() {
        abyssOpened = true;
        Bukkit.broadcastMessage(plugin.getConfig().getString("messages.abyss_opened",
                "§4§l[심연 개문] 세 유물이 모였습니다. 심연이 열립니다!"));
        Bukkit.broadcastMessage(plugin.getConfig().getString("messages.faction_choose",
                "§6진영을 선택하세요: §a수호자 §c타락자 §b탈출자"));
        Bukkit.broadcastMessage(plugin.getConfig().getString("messages.endgame_start",
                "§4§l[엔드게임] 차원 융합 전장이 개시됩니다!"));
    }

    public boolean chooseFaction(Player player, AbyssFaction faction) {
        if (!abyssOpened) return false;
        playerFactions.put(player.getUniqueId(), faction);
        player.sendMessage("§6진영 선택: §e" + faction.getKoreanName());
        return true;
    }

    public AbyssFaction getPlayerFaction(Player player) {
        return playerFactions.get(player.getUniqueId());
    }

    public boolean isAbyssOpened() { return abyssOpened; }
    public Set<Artifact> getCollectedArtifacts() { return Collections.unmodifiableSet(collectedArtifacts); }
}
