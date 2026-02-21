package com.mr.tutorial.managers;

import com.mr.tutorial.TutorialManagerPlugin;
import com.mr.tutorial.models.TutorialStage;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TutorialManager {

    private final TutorialManagerPlugin plugin;
    private final Map<UUID, TutorialStage> playerStages = new HashMap<>();

    public TutorialManager(TutorialManagerPlugin plugin) {
        this.plugin = plugin;
    }

    public TutorialStage getStage(Player player) {
        return playerStages.getOrDefault(player.getUniqueId(), TutorialStage.DIMENSION_ASSIGNED);
    }

    public boolean isCompleted(Player player) {
        return getStage(player) == TutorialStage.COMPLETED;
    }

    public void startTutorial(Player player) {
        playerStages.put(player.getUniqueId(), TutorialStage.DIMENSION_ASSIGNED);
        player.sendMessage(plugin.getConfig().getString("messages.welcome",
                "§6§l과거책: 3천 차원 서버에 오신 것을 환영합니다!"));
        player.sendMessage(plugin.getConfig().getString("messages.worldswitch_hint", ""));
        advanceStage(player);
    }

    public void advanceStage(Player player) {
        TutorialStage current = getStage(player);
        if (current == TutorialStage.COMPLETED) return;
        TutorialStage next = current.next();
        playerStages.put(player.getUniqueId(), next);
        sendStageMessage(player, next);
    }

    private void sendStageMessage(Player player, TutorialStage stage) {
        String msgKey = switch (stage) {
            case COMBAT_BASICS -> "messages.stage_combat";
            case QUEST_BASICS -> "messages.stage_quest";
            case TRADE_BASICS -> "messages.stage_trade";
            case DEATH_BASICS -> "messages.stage_death";
            case COMPLETED -> "messages.completed";
            default -> null;
        };
        if (msgKey != null) {
            player.sendMessage(plugin.getConfig().getString(msgKey, stage.getDescription()));
        }
    }
}
