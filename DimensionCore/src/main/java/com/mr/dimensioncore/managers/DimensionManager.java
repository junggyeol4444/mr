package com.mr.dimensioncore.managers;

import com.mr.dimensioncore.DimensionCorePlugin;
import com.mr.dimensioncore.models.Dimension;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DimensionManager {

    private final DimensionCorePlugin plugin;
    private final Map<UUID, Dimension> playerDimensions = new HashMap<>();
    private final Map<UUID, Long> portalCooldowns = new HashMap<>();

    public DimensionManager(DimensionCorePlugin plugin) {
        this.plugin = plugin;
    }

    public Dimension getPlayerDimension(Player player) {
        return playerDimensions.getOrDefault(player.getUniqueId(), Dimension.FANTASY);
    }

    public void setPlayerDimension(Player player, Dimension dimension) {
        playerDimensions.put(player.getUniqueId(), dimension);
    }

    public boolean isOnPortalCooldown(Player player) {
        long cooldownMs = plugin.getConfig().getLong("portals.cooldown", 60L) * 1000L;
        Long last = portalCooldowns.get(player.getUniqueId());
        return last != null && (System.currentTimeMillis() - last) < cooldownMs;
    }

    public void setPortalCooldown(Player player) {
        portalCooldowns.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public boolean switchDimension(Player player, Dimension target) {
        if (!plugin.getConfig().getBoolean("portals.enabled", true)) return false;
        if (isOnPortalCooldown(player)) return false;
        setPlayerDimension(player, target);
        setPortalCooldown(player);
        return true;
    }
}
