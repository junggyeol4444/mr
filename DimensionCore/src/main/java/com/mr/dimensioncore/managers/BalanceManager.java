package com.mr.dimensioncore.managers;

import com.mr.dimensioncore.DimensionCorePlugin;
import com.mr.dimensioncore.models.Dimension;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class BalanceManager {

    private final DimensionCorePlugin plugin;
    private final Map<Dimension, Double> balanceMap = new EnumMap<>(Dimension.class);
    private BukkitTask balanceTask;
    private final Random random = new Random();

    public BalanceManager(DimensionCorePlugin plugin) {
        this.plugin = plugin;
        double defaultBalance = plugin.getConfig().getDouble("balance.default", 50.0);
        for (Dimension d : Dimension.values()) {
            balanceMap.put(d, defaultBalance);
        }
    }

    public double getBalance(Dimension dimension) {
        return balanceMap.getOrDefault(dimension, 50.0);
    }

    public void setBalance(Dimension dimension, double value) {
        balanceMap.put(dimension, Math.max(0.0, Math.min(100.0, value)));
        checkThresholds(dimension);
    }

    public void adjustBalance(Dimension dimension, double delta) {
        setBalance(dimension, getBalance(dimension) + delta);
    }

    private void checkThresholds(Dimension dimension) {
        double balance = getBalance(dimension);
        double erosion = plugin.getConfig().getDouble("balance.erosion_threshold", 30.0);
        double overcharge = plugin.getConfig().getDouble("balance.overcharge_threshold", 71.0);
        if (balance <= erosion) {
            String msg = plugin.getConfig().getString("messages.balance_erosion",
                    "§4[경고] 차원 침식!")
                    .replace("%balance%", String.format("%.1f", balance));
            Bukkit.broadcastMessage("[DimensionCore] " + msg);
        } else if (balance >= overcharge) {
            String msg = plugin.getConfig().getString("messages.balance_overcharge",
                    "§e[경고] 차원 과충전!")
                    .replace("%balance%", String.format("%.1f", balance));
            Bukkit.broadcastMessage("[DimensionCore] " + msg);
        }
    }

    public void startBalanceTick() {
        long interval = plugin.getConfig().getLong("balance.tick_interval", 200L);
        balanceTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Dimension d : Dimension.values()) {
                double change = (random.nextDouble() - 0.5) * 2.0;
                adjustBalance(d, change);
            }
        }, interval, interval);
    }

    public void stopBalanceTick() {
        if (balanceTask != null) {
            balanceTask.cancel();
        }
    }
}
