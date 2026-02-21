package com.mr.towergate.commands;

import com.mr.towergate.TowerGatePlugin;
import com.mr.towergate.models.TowerFloor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TowerCommand implements CommandExecutor {

    private final TowerGatePlugin plugin;

    public TowerCommand(TowerGatePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있습니다.");
            return true;
        }
        String sub = args.length > 0 ? args[0].toLowerCase() : "info";
        switch (sub) {
            case "info" -> {
                int maxFloor = plugin.getTowerManager().getPlayerMaxFloor(player);
                if (maxFloor == 0) {
                    player.sendMessage(plugin.getConfig().getString("messages.no_record", "§7아직 탑 기록이 없습니다."));
                } else {
                    player.sendMessage(plugin.getConfig().getString("messages.tower_info",
                            "§6=== 탑 정보 ===\n§e최고 층: §f%floor%층")
                            .replace("%floor%", String.valueOf(maxFloor))
                            .replace("%nation_rank%", "N/A"));
                }
            }
            case "floor" -> {
                if (args.length < 2) { player.sendMessage("§c사용법: /tower floor <층>"); return true; }
                try {
                    int floorNum = Integer.parseInt(args[1]);
                    TowerFloor floor = plugin.getTowerManager().getFloor(floorNum);
                    if (floor == null) {
                        player.sendMessage("§c유효하지 않은 층입니다: " + floorNum);
                    } else {
                        player.sendMessage(plugin.getConfig().getString("messages.floor_info",
                                "§6[탑 %floor%층] §e테마: %theme%")
                                .replace("%floor%", String.valueOf(floor.getFloorNumber()))
                                .replace("%theme%", floor.getTheme()));
                        if (floor.isForbidden()) player.sendMessage(plugin.getConfig().getString("messages.floor_forbidden", "§c금기층!"));
                        if (floor.isTimeLimited()) player.sendMessage(plugin.getConfig().getString("messages.floor_time_limit", "§e시간제한층!"));
                    }
                } catch (NumberFormatException e) {
                    player.sendMessage("§c숫자를 입력해주세요.");
                }
            }
            case "rank" -> {
                player.sendMessage(plugin.getConfig().getString("messages.rank_header", "§6=== 개인 랭킹 ==="));
                var rankings = plugin.getTowerManager().getPersonalRankings();
                int rank = 1;
                for (var entry : rankings) {
                    if (rank > 10) break;
                    player.sendMessage("§e" + rank + "위: §f" + entry.getKey() + " §7(" + entry.getValue() + "층)");
                    rank++;
                }
            }
            default -> player.sendMessage("§c사용법: /tower [info|floor <층>|rank]");
        }
        return true;
    }
}
