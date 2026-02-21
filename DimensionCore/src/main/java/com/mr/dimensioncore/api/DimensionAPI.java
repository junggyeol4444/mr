package com.mr.dimensioncore.api;

import com.mr.dimensioncore.DimensionCorePlugin;
import com.mr.dimensioncore.models.Dimension;
import org.bukkit.entity.Player;

public class DimensionAPI {

    private final DimensionCorePlugin plugin;

    public DimensionAPI(DimensionCorePlugin plugin) {
        this.plugin = plugin;
    }

    public Dimension getPlayerDimension(Player player) {
        return plugin.getDimensionManager().getPlayerDimension(player);
    }

    public void setPlayerDimension(Player player, Dimension dimension) {
        plugin.getDimensionManager().setPlayerDimension(player, dimension);
    }

    public double getDimensionBalance(Dimension dimension) {
        return plugin.getBalanceManager().getBalance(dimension);
    }

    public void adjustDimensionBalance(Dimension dimension, double delta) {
        plugin.getBalanceManager().adjustBalance(dimension, delta);
    }

    public boolean isErosion(Dimension dimension) {
        return getDimensionBalance(dimension) <= plugin.getConfig().getDouble("balance.erosion_threshold", 30.0);
    }

    public boolean isOvercharge(Dimension dimension) {
        return getDimensionBalance(dimension) >= plugin.getConfig().getDouble("balance.overcharge_threshold", 71.0);
    }

    public static DimensionAPI get() {
        return DimensionCorePlugin.getInstance().getDimensionAPI();
    }
}
