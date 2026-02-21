package com.mr.statsystem.managers;

import com.mr.statsystem.StatSystemPlugin;
import com.mr.statsystem.models.StatType;
import org.bukkit.entity.Player;

import java.util.*;

public class StatManager {

    private final StatSystemPlugin plugin;
    private final Map<UUID, Map<StatType, Integer>> playerStats = new HashMap<>();
    private final Map<UUID, Integer> playerLevels = new HashMap<>();
    private final Map<UUID, Long> playerExp = new HashMap<>();
    private final Map<UUID, Integer> playerStatPoints = new HashMap<>();

    public StatManager(StatSystemPlugin plugin) {
        this.plugin = plugin;
    }

    private void initPlayer(Player player) {
        if (!playerStats.containsKey(player.getUniqueId())) {
            Map<StatType, Integer> stats = new EnumMap<>(StatType.class);
            for (StatType st : StatType.values()) {
                int base = plugin.getConfig().getInt("stats.base." + st.name(), 0);
                stats.put(st, base);
            }
            playerStats.put(player.getUniqueId(), stats);
            playerLevels.put(player.getUniqueId(), 1);
            playerExp.put(player.getUniqueId(), 0L);
            playerStatPoints.put(player.getUniqueId(), 0);
        }
    }

    public int getStat(Player player, StatType type) {
        initPlayer(player);
        return playerStats.get(player.getUniqueId()).getOrDefault(type, 0);
    }

    public void setStat(Player player, StatType type, int value) {
        initPlayer(player);
        playerStats.get(player.getUniqueId()).put(type, Math.max(0, value));
    }

    public void addStat(Player player, StatType type, int amount) {
        setStat(player, type, getStat(player, type) + amount);
    }

    public int getLevel(Player player) {
        initPlayer(player);
        return playerLevels.getOrDefault(player.getUniqueId(), 1);
    }

    public long getExp(Player player) {
        initPlayer(player);
        return playerExp.getOrDefault(player.getUniqueId(), 0L);
    }

    public long getExpForLevel(int level) {
        return (long) level * plugin.getConfig().getLong("leveling.exp_per_level_multiplier", 100L);
    }

    public void addExp(Player player, long amount) {
        initPlayer(player);
        UUID uuid = player.getUniqueId();
        long newExp = playerExp.get(uuid) + amount;
        int level = playerLevels.get(uuid);
        int maxLevel = plugin.getConfig().getInt("leveling.max_level", 999);

        while (level < maxLevel && newExp >= getExpForLevel(level)) {
            newExp -= getExpForLevel(level);
            level++;
            int pointsPerLevel = plugin.getConfig().getInt("leveling.stat_points_per_level", 3);
            playerStatPoints.put(uuid, playerStatPoints.getOrDefault(uuid, 0) + pointsPerLevel);
            String levelUpMsg = plugin.getConfig().getString("messages.level_up", "§6§l레벨 업! §a레벨 %level%!")
                    .replace("%level%", String.valueOf(level));
            player.sendMessage(levelUpMsg);
        }

        playerLevels.put(uuid, level);
        playerExp.put(uuid, newExp);
    }

    public int getStatPoints(Player player) {
        initPlayer(player);
        return playerStatPoints.getOrDefault(player.getUniqueId(), 0);
    }

    public boolean spendStatPoint(Player player, StatType type, int amount) {
        initPlayer(player);
        int points = getStatPoints(player);
        if (points < amount) return false;
        playerStatPoints.put(player.getUniqueId(), points - amount);
        addStat(player, type, amount);
        return true;
    }

    public Map<StatType, Integer> getAllStats(Player player) {
        initPlayer(player);
        return Collections.unmodifiableMap(playerStats.get(player.getUniqueId()));
    }
}
