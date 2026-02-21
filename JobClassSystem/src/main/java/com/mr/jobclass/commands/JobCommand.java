package com.mr.jobclass.commands;

import com.mr.jobclass.JobClassPlugin;
import com.mr.jobclass.models.AwakeningType;
import com.mr.jobclass.models.FantasyJob;
import com.mr.jobclass.models.HunterRank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobCommand implements CommandExecutor {

    private final JobClassPlugin plugin;

    public JobCommand(JobClassPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있습니다.");
            return true;
        }
        if (args.length == 0 || args[0].equalsIgnoreCase("info")) {
            showJobInfo(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("select") && args.length >= 2) {
            FantasyJob job = FantasyJob.fromString(args[1]);
            if (job == null) {
                player.sendMessage(plugin.getConfig().getString("messages.invalid_job",
                        "§c유효하지 않은 직업입니다: %job%").replace("%job%", args[1]));
                return true;
            }
            boolean success = plugin.getJobManager().selectFantasyJob(player, job);
            if (success) {
                player.sendMessage(plugin.getConfig().getString("messages.job_selected",
                        "§a직업을 선택했습니다: §e%job%").replace("%job%", job.getKoreanName()));
            } else {
                player.sendMessage(plugin.getConfig().getString("messages.job_already_selected",
                        "§c이미 직업이 있습니다."));
            }
            return true;
        }
        player.sendMessage("§c사용법: /job [select <직업>|info]");
        return true;
    }

    private void showJobInfo(Player player) {
        FantasyJob job = plugin.getJobManager().getFantasyJob(player);
        HunterRank rank = plugin.getJobManager().getHunterRank(player);
        AwakeningType awakening = plugin.getJobManager().getAwakening(player);

        if (job != null) {
            player.sendMessage(plugin.getConfig().getString("messages.job_info",
                    "§6현재 직업: §e%job%").replace("%job%", job.getKoreanName()));
        } else {
            player.sendMessage("§7현재 직업: §c없음");
            player.sendMessage("§7판타지 직업 목록: /job select <직업명>");
        }
        player.sendMessage(plugin.getConfig().getString("messages.rank_info",
                "§6헌터 랭크: §e%rank% §7| 각성: §b%awakening%")
                .replace("%rank%", rank.getDisplayName())
                .replace("%awakening%", awakening != null ? awakening.getKoreanName() : "미각성"));
    }
}
