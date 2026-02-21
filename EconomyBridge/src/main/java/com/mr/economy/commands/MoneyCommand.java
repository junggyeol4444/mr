package com.mr.economy.commands;

import com.mr.economy.EconomyBridgePlugin;
import com.mr.economy.models.Currency;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class MoneyCommand implements CommandExecutor {

    private final EconomyBridgePlugin plugin;

    public MoneyCommand(EconomyBridgePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있습니다.");
            return true;
        }
        String sub = args.length > 0 ? args[0].toLowerCase() : "balance";
        switch (sub) {
            case "balance" -> showBalance(player);
            case "pay" -> handlePay(player, args);
            case "exchange" -> handleExchange(player, args);
            default -> player.sendMessage("§c사용법: /money [balance|pay <플레이어> <금액> <통화>|exchange <금액> <from> <to>]");
        }
        return true;
    }

    private void showBalance(Player player) {
        Map<Currency, Long> balances = plugin.getEconomyManager().getAllBalances(player);
        player.sendMessage("§6=== 잔액 ===");
        for (Map.Entry<Currency, Long> entry : balances.entrySet()) {
            if (entry.getValue() > 0) {
                player.sendMessage("§e" + entry.getKey().getKoreanName() + ": §f" + entry.getValue());
            }
        }
    }

    private void handlePay(Player player, String[] args) {
        if (args.length < 4) {
            player.sendMessage("§c사용법: /money pay <플레이어> <금액> <통화>");
            return;
        }
        Player target = Bukkit.getPlayerExact(args[1]);
        if (target == null) {
            player.sendMessage("§c플레이어를 찾을 수 없습니다: " + args[1]);
            return;
        }
        long amount;
        try { amount = Long.parseLong(args[2]); } catch (NumberFormatException e) {
            player.sendMessage("§c올바른 금액을 입력해주세요.");
            return;
        }
        Currency currency = Currency.fromString(args[3]);
        if (currency == null) {
            player.sendMessage(plugin.getConfig().getString("messages.invalid_currency", "§c유효하지 않은 통화입니다.")
                    .replace("%currency%", args[3]));
            return;
        }
        boolean success = plugin.getEconomyManager().transfer(player, target, currency, amount);
        if (success) {
            player.sendMessage(plugin.getConfig().getString("messages.pay_success",
                    "§a%target%에게 §e%amount% %currency%§a를 송금했습니다.")
                    .replace("%target%", target.getName())
                    .replace("%amount%", String.valueOf(amount))
                    .replace("%currency%", currency.getKoreanName()));
        } else {
            player.sendMessage(plugin.getConfig().getString("messages.pay_insufficient", "§c잔액이 부족합니다."));
        }
    }

    private void handleExchange(Player player, String[] args) {
        if (args.length < 4) {
            player.sendMessage("§c사용법: /money exchange <금액> <from통화> <to통화>");
            return;
        }
        long amount;
        try { amount = Long.parseLong(args[1]); } catch (NumberFormatException e) {
            player.sendMessage("§c올바른 금액을 입력해주세요.");
            return;
        }
        Currency from = Currency.fromString(args[2]);
        Currency to = Currency.fromString(args[3]);
        if (from == null || to == null) {
            player.sendMessage("§c유효하지 않은 통화입니다.");
            return;
        }
        boolean success = plugin.getExchangeManager().exchange(player, from, to, amount);
        if (success) {
            double rate = plugin.getExchangeManager().getExchangeRate(from, to);
            player.sendMessage(plugin.getConfig().getString("messages.exchange_success",
                    "§a환전 완료!")
                    .replace("%from_amount%", String.valueOf(amount))
                    .replace("%from%", from.getKoreanName())
                    .replace("%to_amount%", String.valueOf((long)(amount * rate)))
                    .replace("%to%", to.getKoreanName()));
        } else {
            player.sendMessage("§c환전에 실패했습니다. 잔액을 확인하거나 지원되는 환율인지 확인하세요.");
        }
    }
}
