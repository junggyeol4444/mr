package com.mr.economy.managers;

import com.mr.economy.EconomyBridgePlugin;
import com.mr.economy.models.Currency;
import org.bukkit.entity.Player;

public class ExchangeManager {

    private final EconomyBridgePlugin plugin;

    public ExchangeManager(EconomyBridgePlugin plugin) {
        this.plugin = plugin;
    }

    public double getExchangeRate(Currency from, Currency to) {
        String key = from.name() + "_to_" + to.name();
        if (plugin.getConfig().contains("exchange_rates." + key)) {
            return plugin.getConfig().getDouble("exchange_rates." + key, 1.0);
        }
        String reverseKey = to.name() + "_to_" + from.name();
        if (plugin.getConfig().contains("exchange_rates." + reverseKey)) {
            return 1.0 / plugin.getConfig().getDouble("exchange_rates." + reverseKey, 1.0);
        }
        return -1.0;
    }

    public boolean exchange(Player player, Currency from, Currency to, long amount) {
        double rate = getExchangeRate(from, to);
        if (rate <= 0) return false;
        long toAmount = (long) (amount * rate);
        if (!plugin.getEconomyManager().deductBalance(player, from, amount)) return false;
        plugin.getEconomyManager().addBalance(player, to, toAmount);
        return true;
    }
}
