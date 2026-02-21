package com.mr.statsystem.commands;

import com.mr.statsystem.StatSystemPlugin;
import com.mr.statsystem.models.StatType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatPointCommand implements CommandExecutor {

    private final StatSystemPlugin plugin;

    public StatPointCommand(StatSystemPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있습니다.");
            return true;
        }
        if (args.length < 2) {
            player.sendMessage("§c사용법: /statpoint <스탯> <포인트>");
            return true;
        }
        StatType type = StatType.fromString(args[0]);
        if (type == null) {
            player.sendMessage(plugin.getConfig().getString("messages.statpoint_invalid", "§c유효하지 않은 스탯입니다: %stat%")
                    .replace("%stat%", args[0]));
            return true;
        }
        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage("§c숫자를 입력해주세요.");
            return true;
        }
        if (amount <= 0) {
            player.sendMessage("§c포인트는 1 이상이어야 합니다.");
            return true;
        }
        boolean success = plugin.getStatManager().spendStatPoint(player, type, amount);
        if (success) {
            player.sendMessage(plugin.getConfig().getString("messages.statpoint_success",
                    "§a%stat%에 %amount% 포인트를 투자했습니다.")
                    .replace("%stat%", type.getKoreanName())
                    .replace("%amount%", String.valueOf(amount)));
        } else {
            player.sendMessage(plugin.getConfig().getString("messages.statpoint_insufficient",
                    "§c스탯 포인트가 부족합니다."));
        }
        return true;
    }
}
