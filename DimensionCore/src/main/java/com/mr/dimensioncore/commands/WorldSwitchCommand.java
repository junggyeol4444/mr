package com.mr.dimensioncore.commands;

import com.mr.dimensioncore.DimensionCorePlugin;
import com.mr.dimensioncore.models.Dimension;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WorldSwitchCommand implements CommandExecutor {

    private final DimensionCorePlugin plugin;
    private final Set<UUID> usedSwitch = new HashSet<>();

    public WorldSwitchCommand(DimensionCorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있습니다.");
            return true;
        }
        if (args.length < 1) {
            player.sendMessage("§c사용법: /worldswitch <차원>");
            return true;
        }
        if (usedSwitch.contains(player.getUniqueId())) {
            player.sendMessage(plugin.getConfig().getString("messages.worldswitch_used",
                    "§c이미 차원 변경을 사용했습니다!"));
            return true;
        }
        Dimension target = Dimension.fromString(args[0]);
        if (target == null) {
            String msg = plugin.getConfig().getString("messages.unknown_dimension",
                    "§c알 수 없는 차원입니다: %name%")
                    .replace("%name%", args[0]);
            player.sendMessage(msg);
            return true;
        }
        usedSwitch.add(player.getUniqueId());
        plugin.getDimensionManager().setPlayerDimension(player, target);
        String msg = plugin.getConfig().getString("messages.worldswitch_success",
                "§a차원을 이동합니다: %dimension%")
                .replace("%dimension%", target.getKoreanName());
        player.sendMessage(msg);
        return true;
    }
}
