package com.mr.jobclass.managers;

import com.mr.jobclass.JobClassPlugin;
import com.mr.jobclass.models.*;
import org.bukkit.entity.Player;

import java.util.*;

public class JobManager {

    private final JobClassPlugin plugin;
    private final Map<UUID, FantasyJob> playerFantasyJobs = new HashMap<>();
    private final Map<UUID, HunterRank> playerHunterRanks = new HashMap<>();
    private final Map<UUID, AwakeningType> playerAwakenings = new HashMap<>();
    private final Map<UUID, Set<MartialArt>> playerMartialArts = new HashMap<>();
    private final Map<UUID, Boolean> hasRareArt = new HashMap<>();
    private final Random random = new Random();

    public JobManager(JobClassPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean selectFantasyJob(Player player, FantasyJob job) {
        if (playerFantasyJobs.containsKey(player.getUniqueId())) return false;
        playerFantasyJobs.put(player.getUniqueId(), job);
        return true;
    }

    public FantasyJob getFantasyJob(Player player) {
        return playerFantasyJobs.get(player.getUniqueId());
    }

    public HunterRank getHunterRank(Player player) {
        return playerHunterRanks.getOrDefault(player.getUniqueId(), HunterRank.E);
    }

    public void setHunterRank(Player player, HunterRank rank) {
        playerHunterRanks.put(player.getUniqueId(), rank);
    }

    public void promoteHunterRank(Player player) {
        HunterRank current = getHunterRank(player);
        setHunterRank(player, current.next());
    }

    public AwakeningType getAwakening(Player player) {
        return playerAwakenings.get(player.getUniqueId());
    }

    public boolean setAwakening(Player player, AwakeningType type) {
        if (playerAwakenings.containsKey(player.getUniqueId())) return false;
        playerAwakenings.put(player.getUniqueId(), type);
        return true;
    }

    public boolean learnMartialArt(Player player, MartialArt art) {
        Set<MartialArt> learned = playerMartialArts.computeIfAbsent(
                player.getUniqueId(), k -> new HashSet<>());

        if (hasRareArt.getOrDefault(player.getUniqueId(), false)) {
            return learned.add(art);
        }

        if (!learned.isEmpty()) {
            double penalty = plugin.getConfig().getDouble("martial_arts.learning_penalty", 0.05);
            if (random.nextDouble() > penalty) {
                return false;
            }
        }
        return learned.add(art);
    }

    public void grantRareArt(Player player) {
        hasRareArt.put(player.getUniqueId(), true);
    }

    public boolean hasRareArt(Player player) {
        return hasRareArt.getOrDefault(player.getUniqueId(), false);
    }

    public Set<MartialArt> getMartialArts(Player player) {
        return Collections.unmodifiableSet(
                playerMartialArts.getOrDefault(player.getUniqueId(), Collections.emptySet()));
    }
}
