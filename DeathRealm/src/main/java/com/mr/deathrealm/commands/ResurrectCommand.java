package com.mr.deathrealm.commands;

import com.mr.deathrealm.DeathRealmPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResurrectCommand implements CommandExecutor {

    private final DeathRealmPlugin plugin;

    public ResurrectCommand(DeathRealmPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있습니다.");
            return true;
        }
        long cost = plugin.getDeathRealmManager().getCurrentResurrectionCost();
        player.sendMessage(plugin.getConfig().getString("messages.resurrect_cost", "§6부활 비용: §e%cost% 소울")
                .replace("%cost%", String.valueOf(cost)));

        boolean success = plugin.getDeathRealmManager().resurrect(player, true);
        if (!success) {
            player.sendMessage(plugin.getConfig().getString("messages.resurrect_insufficient_soul",
                    "§c소울이 부족합니다. 부활 비용: %cost% 소울")
                    .replace("%cost%", String.valueOf(cost)));
        }
        return true;
    }
}
