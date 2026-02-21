package com.mr.questengine.managers;

import com.mr.questengine.QuestEnginePlugin;
import com.mr.questengine.models.Quest;
import com.mr.questengine.models.QuestStatus;
import com.mr.questengine.models.QuestType;
import org.bukkit.entity.Player;

import java.util.*;

public class QuestManager {

    private final QuestEnginePlugin plugin;
    private final Map<String, Quest> questRegistry = new HashMap<>();
    private final Map<UUID, Map<String, QuestStatus>> playerQuestStatus = new HashMap<>();

    public QuestManager(QuestEnginePlugin plugin) {
        this.plugin = plugin;
        loadDefaultQuests();
    }

    private void loadDefaultQuests() {
        questRegistry.put("tutorial_combat", new Quest(
                "tutorial_combat", "첫 전투", QuestType.PERSONAL,
                "몬스터를 처음 처치하세요.", List.of("몬스터 1마리 처치"), 100));
        questRegistry.put("explore_world", new Quest(
                "explore_world", "세계 탐험", QuestType.FREE,
                "새로운 차원을 탐험하세요.", List.of("새 차원 도달"), 200));
        questRegistry.put("main_lumen", new Quest(
                "main_lumen", "루멘의 불씨를 찾아서", QuestType.MAIN,
                "판타지 차원의 고대 유물 루멘의 불씨를 찾으세요.",
                List.of("폐허의 왕좌 방문", "루멘 폐도시 탐색", "불씨 획득"), 5000));
        questRegistry.put("gate_event_001", new Quest(
                "gate_event_001", "게이트 토벌", QuestType.GATE_EVENT,
                "나타난 게이트를 봉인하세요.", List.of("게이트 보스 처치", "게이트 봉인"), 1000));
    }

    public Quest getQuest(String id) {
        return questRegistry.get(id);
    }

    public Collection<Quest> getAllQuests() {
        return Collections.unmodifiableCollection(questRegistry.values());
    }

    public QuestStatus getStatus(Player player, String questId) {
        return playerQuestStatus
                .getOrDefault(player.getUniqueId(), Collections.emptyMap())
                .getOrDefault(questId, QuestStatus.NOT_STARTED);
    }

    public boolean acceptQuest(Player player, String questId) {
        Quest quest = questRegistry.get(questId);
        if (quest == null) return false;
        QuestStatus status = getStatus(player, questId);
        if (status != QuestStatus.NOT_STARTED) return false;
        playerQuestStatus.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
                .put(questId, QuestStatus.IN_PROGRESS);
        return true;
    }

    public boolean abandonQuest(Player player, String questId) {
        QuestStatus status = getStatus(player, questId);
        if (status != QuestStatus.IN_PROGRESS) return false;
        playerQuestStatus.get(player.getUniqueId()).put(questId, QuestStatus.NOT_STARTED);
        return true;
    }

    public void completeQuest(Player player, String questId) {
        playerQuestStatus.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
                .put(questId, QuestStatus.COMPLETED);
        Quest quest = questRegistry.get(questId);
        if (quest != null) {
            String msg = plugin.getConfig().getString("messages.quest_completed", "§6§l퀘스트 완료!")
                    .replace("%quest%", quest.getName());
            player.sendMessage(msg);
        }
    }

    public List<Quest> getActiveQuests(Player player) {
        Map<String, QuestStatus> statuses = playerQuestStatus.getOrDefault(
                player.getUniqueId(), Collections.emptyMap());
        List<Quest> active = new ArrayList<>();
        for (Map.Entry<String, QuestStatus> entry : statuses.entrySet()) {
            if (entry.getValue() == QuestStatus.IN_PROGRESS) {
                Quest q = questRegistry.get(entry.getKey());
                if (q != null) active.add(q);
            }
        }
        return active;
    }
}
