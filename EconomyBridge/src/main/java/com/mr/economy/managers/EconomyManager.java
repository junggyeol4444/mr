package com.mr.economy.managers;

import com.mr.economy.EconomyBridgePlugin;
import com.mr.economy.models.Currency;
import org.bukkit.entity.Player;

import java.util.*;

public class EconomyManager {

    private final EconomyBridgePlugin plugin;
    private final Map<UUID, Map<Currency, Long>> balances = new HashMap<>();

    public EconomyManager(EconomyBridgePlugin plugin) {
        this.plugin = plugin;
    }

    private void initPlayer(Player player) {
        if (!balances.containsKey(player.getUniqueId())) {
            Map<Currency, Long> playerBalance = new EnumMap<>(Currency.class);
            for (Currency c : Currency.values()) {
                playerBalance.put(c, 0L);
            }
            balances.put(player.getUniqueId(), playerBalance);
            // 시작 잔액 지급
            addBalance(player, Currency.COPPER_COIN, plugin.getConfig().getLong("starting_balance.fantasy", 100));
            addBalance(player, Currency.COPPER_LIANG, plugin.getConfig().getLong("starting_balance.martial", 100));
            addBalance(player, Currency.COIN, plugin.getConfig().getLong("starting_balance.earth", 100));
            addBalance(player, Currency.SOUL, plugin.getConfig().getLong("starting_balance.netherworld", 10));
        }
    }

    public long getBalance(Player player, Currency currency) {
        initPlayer(player);
        return balances.get(player.getUniqueId()).getOrDefault(currency, 0L);
    }

    public void addBalance(Player player, Currency currency, long amount) {
        initPlayer(player);
        Map<Currency, Long> b = balances.get(player.getUniqueId());
        b.put(currency, b.getOrDefault(currency, 0L) + amount);
    }

    public boolean deductBalance(Player player, Currency currency, long amount) {
        initPlayer(player);
        long current = getBalance(player, currency);
        if (current < amount) return false;
        balances.get(player.getUniqueId()).put(currency, current - amount);
        return true;
    }

    public boolean transfer(Player from, Player to, Currency currency, long amount) {
        if (!deductBalance(from, currency, amount)) return false;
        addBalance(to, currency, amount);
        return true;
    }

    public Map<Currency, Long> getAllBalances(Player player) {
        initPlayer(player);
        return Collections.unmodifiableMap(balances.get(player.getUniqueId()));
    }
}
