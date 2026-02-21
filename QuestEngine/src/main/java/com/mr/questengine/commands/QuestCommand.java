package com.mr.questengine.commands;

import com.mr.questengine.QuestEnginePlugin;
import com.mr.questengine.models.Quest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class QuestCommand implements CommandExecutor {

    private final QuestEnginePlugin plugin;

    public QuestCommand(QuestEnginePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있습니다.");
            return true;
        }
        if (args.length == 0 || args[0].equalsIgnoreCase("list")) {
            List<Quest> active = plugin.getQuestManager().getActiveQuests(player);
            if (active.isEmpty()) {
                player.sendMessage(plugin.getConfig().getString("messages.no_active_quests",
                        "§7진행 중인 퀘스트가 없습니다."));
            } else {
                player.sendMessage(plugin.getConfig().getString("messages.quest_list_header",
                        "§6=== 진행 중인 퀘스트 ==="));
                for (Quest q : active) {
                    player.sendMessage("§e- " + q.getName() + " §7[" + q.getType().getKoreanName() + "]");
                }
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("accept") && args.length >= 2) {
            boolean success = plugin.getQuestManager().acceptQuest(player, args[1]);
            if (success) {
                Quest q = plugin.getQuestManager().getQuest(args[1]);
                player.sendMessage(plugin.getConfig().getString("messages.quest_accepted",
                        "§a퀘스트를 수락했습니다: §e%quest%")
                        .replace("%quest%", q != null ? q.getName() : args[1]));
            } else {
                player.sendMessage(plugin.getConfig().getString("messages.quest_not_found",
                        "§c퀘스트를 찾을 수 없습니다: %id%").replace("%id%", args[1]));
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("abandon") && args.length >= 2) {
            boolean success = plugin.getQuestManager().abandonQuest(player, args[1]);
            Quest q = plugin.getQuestManager().getQuest(args[1]);
            if (success) {
                player.sendMessage(plugin.getConfig().getString("messages.quest_abandoned",
                        "§c퀘스트를 포기했습니다: §e%quest%")
                        .replace("%quest%", q != null ? q.getName() : args[1]));
            } else {
                player.sendMessage("§c진행 중인 퀘스트가 아닙니다.");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("info") && args.length >= 2) {
            Quest q = plugin.getQuestManager().getQuest(args[1]);
            if (q == null) {
                player.sendMessage(plugin.getConfig().getString("messages.quest_not_found",
                        "§c퀘스트를 찾을 수 없습니다: %id%").replace("%id%", args[1]));
            } else {
                player.sendMessage("§6[" + q.getType().getKoreanName() + "] §e" + q.getName());
                player.sendMessage("§7" + q.getDescription());
                player.sendMessage("§a보상 경험치: §f" + q.getExpReward());
            }
            return true;
        }
        player.sendMessage("§c사용법: /quest [list|info <ID>|accept <ID>|abandon <ID>]");
        return true;
    }
}
