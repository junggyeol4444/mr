package com.mr.statsystem.commands;

import com.mr.statsystem.StatSystemPlugin;
import com.mr.statsystem.models.StatType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class StatsCommand implements CommandExecutor {

    private final StatSystemPlugin plugin;

    public StatsCommand(StatSystemPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target;
        if (args.length > 0) {
            target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage("§c플레이어를 찾을 수 없습니다: " + args[0]);
                return true;
            }
        } else if (sender instanceof Player p) {
            target = p;
        } else {
            sender.sendMessage("§c플레이어를 지정해야 합니다.");
            return true;
        }

        int level = plugin.getStatManager().getLevel(target);
        long exp = plugin.getStatManager().getExp(target);
        long maxExp = plugin.getStatManager().getExpForLevel(level);
        int points = plugin.getStatManager().getStatPoints(target);

        sender.sendMessage(plugin.getConfig().getString("messages.stats_header", "§6=== %player% 스탯 ===")
                .replace("%player%", target.getName()));
        sender.sendMessage(plugin.getConfig().getString("messages.level_line", "§a레벨: §f%level%")
                .replace("%level%", String.valueOf(level))
                .replace("%exp%", String.valueOf(exp))
                .replace("%max_exp%", String.valueOf(maxExp)));
        sender.sendMessage(plugin.getConfig().getString("messages.stat_points", "§b남은 스탯 포인트: §f%points%")
                .replace("%points%", String.valueOf(points)));

        Map<StatType, Integer> stats = plugin.getStatManager().getAllStats(target);
        for (Map.Entry<StatType, Integer> entry : stats.entrySet()) {
            if (entry.getValue() > 0) {
                sender.sendMessage(plugin.getConfig().getString("messages.stat_line", "§e%stat%: §f%value%")
                        .replace("%stat%", entry.getKey().getKoreanName())
                        .replace("%value%", String.valueOf(entry.getValue())));
            }
        }
        return true;
    }
}
