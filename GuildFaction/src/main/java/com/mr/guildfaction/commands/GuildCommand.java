package com.mr.guildfaction.commands;

import com.mr.guildfaction.GuildFactionPlugin;
import com.mr.guildfaction.models.Guild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class GuildCommand implements CommandExecutor {

    private final GuildFactionPlugin plugin;

    public GuildCommand(GuildFactionPlugin plugin) {
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
            case "create" -> {
                if (args.length < 2) { player.sendMessage("§c사용법: /guild create <이름>"); return true; }
                if (plugin.getGuildManager().isInGuild(player)) {
                    player.sendMessage(plugin.getConfig().getString("messages.already_in_guild",
                            "§c이미 길드에 소속되어 있습니다."));
                    return true;
                }
                String guildName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                Guild guild = plugin.getGuildManager().createGuild(player, guildName);
                player.sendMessage(plugin.getConfig().getString("messages.guild_created",
                        "§a길드 '%name%'이(가) 창설되었습니다!")
                        .replace("%name%", guild.getName()));
            }
            case "info" -> {
                Guild guild = plugin.getGuildManager().getPlayerGuild(player);
                if (guild == null) {
                    player.sendMessage(plugin.getConfig().getString("messages.not_in_guild",
                            "§c길드에 소속되어 있지 않습니다."));
                } else {
                    int max = plugin.getConfig().getInt("guild.max_members", 50);
                    player.sendMessage(plugin.getConfig().getString("messages.guild_info",
                            "§6=== 길드: %name% ===\n§e길드장: §f%leader%\n§e인원: §f%members%/%max%")
                            .replace("%name%", guild.getName())
                            .replace("%leader%", guild.getLeader().toString())
                            .replace("%members%", String.valueOf(guild.getMemberCount()))
                            .replace("%max%", String.valueOf(max)));
                }
            }
            case "leave" -> {
                if (!plugin.getGuildManager().isInGuild(player)) {
                    player.sendMessage(plugin.getConfig().getString("messages.not_in_guild",
                            "§c길드에 소속되어 있지 않습니다."));
                    return true;
                }
                plugin.getGuildManager().leaveGuild(player);
                player.sendMessage(plugin.getConfig().getString("messages.guild_left", "§c길드에서 탈퇴했습니다."));
            }
            case "disband" -> {
                Guild guild = plugin.getGuildManager().getPlayerGuild(player);
                if (guild == null) {
                    player.sendMessage(plugin.getConfig().getString("messages.not_in_guild", "§c길드가 없습니다."));
                    return true;
                }
                if (!guild.getLeader().equals(player.getUniqueId())) {
                    player.sendMessage("§c길드장만 해산할 수 있습니다.");
                    return true;
                }
                plugin.getGuildManager().disbandGuild(guild);
                player.sendMessage(plugin.getConfig().getString("messages.guild_disbanded", "§c길드가 해산되었습니다."));
            }
            default -> player.sendMessage("§c사용법: /guild [create <이름>|info|leave|disband]");
        }
        return true;
    }

    private static String[] Arrays_copyOfRange(String[] arr, int from, int to) {
        return java.util.Arrays.copyOfRange(arr, from, to);
    }
}
