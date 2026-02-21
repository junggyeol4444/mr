package com.mr.economy.managers;

import com.mr.economy.EconomyBridgePlugin;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SoulBankManager {

    private final EconomyBridgePlugin plugin;
    private final Map<UUID, Long> deposits = new HashMap<>();

    public SoulBankManager(EconomyBridgePlugin plugin) {
        this.plugin = plugin;
    }

    public long getDeposit(Player player) {
        return deposits.getOrDefault(player.getUniqueId(), 0L);
    }

    public boolean deposit(Player player, long amount) {
        long maxDeposit = plugin.getConfig().getLong("soul_bank.max_deposit", 10000000L);
        long current = getDeposit(player);
        if (current + amount > maxDeposit) return false;
        if (!plugin.getEconomyManager().deductBalance(player,
                com.mr.economy.models.Currency.SOUL, amount)) return false;
        deposits.put(player.getUniqueId(), current + amount);
        return true;
    }

    public boolean withdraw(Player player, long amount) {
        long current = getDeposit(player);
        if (current < amount) return false;
        deposits.put(player.getUniqueId(), current - amount);
        plugin.getEconomyManager().addBalance(player,
                com.mr.economy.models.Currency.SOUL, amount);
        return true;
    }

    public boolean hasSufficientFunds(Player player, long amount) {
        return getDeposit(player) >= amount;
    }
}
