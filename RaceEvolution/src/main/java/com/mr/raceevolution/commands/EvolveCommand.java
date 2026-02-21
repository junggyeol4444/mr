package com.mr.raceevolution.commands;

import com.mr.raceevolution.RaceEvolutionPlugin;
import com.mr.raceevolution.models.EvolutionPath;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EvolveCommand implements CommandExecutor {

    private final RaceEvolutionPlugin plugin;

    public EvolveCommand(RaceEvolutionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있습니다.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("transcend")) {
            plugin.getEvolutionManager().transcend(player);
            return true;
        }

        if (args.length < 1) {
            player.sendMessage("§c사용법: /evolve <경로>");
            player.sendMessage("§7경로: SPIRIT_HUMAN, HALF_DEMON, MECHANICAL_HUMAN, CORRUPTION");
            return true;
        }
        if (args[0].equalsIgnoreCase("confirm") && args.length >= 2) {
            EvolutionPath path = EvolutionPath.fromString(args[1]);
            if (path == null) {
                player.sendMessage("§c유효하지 않은 진화 경로입니다: " + args[1]);
                return true;
            }
            plugin.getEvolutionManager().evolve(player, path);
            return true;
        }
        EvolutionPath path = EvolutionPath.fromString(args[0]);
        if (path == null) {
            player.sendMessage("§c유효하지 않은 진화 경로입니다: " + args[0]);
            return true;
        }
        player.sendMessage(plugin.getConfig().getString("messages.evolve_prompt",
                "§6종족 진화는 되돌릴 수 없습니다.")
                .replace("%path%", path.getKoreanName()));
        return true;
    }
}
