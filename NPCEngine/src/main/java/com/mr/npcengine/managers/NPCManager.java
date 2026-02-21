package com.mr.npcengine.managers;

import com.mr.npcengine.NPCEnginePlugin;
import com.mr.npcengine.models.NPC;
import com.mr.npcengine.models.NPCRelation;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NPCManager {

    private final NPCEnginePlugin plugin;
    private final Map<String, NPC> npcs = new HashMap<>();

    public NPCManager(NPCEnginePlugin plugin) {
        this.plugin = plugin;
        loadDefaultNPCs();
    }

    private void loadDefaultNPCs() {
        // 기본 NPC 예시
        register(new NPC("merchant_01", "상인 리우", "fantasy"));
        register(new NPC("guard_01", "위병대장 카엘", "fantasy"));
        register(new NPC("sage_01", "현자 무건", "martial"));
        register(new NPC("hunter_01", "헌터 지수", "earth"));
        register(new NPC("netherworld_judge", "명계 심판관", "netherworld"));
    }

    public void register(NPC npc) {
        npcs.put(npc.getId(), npc);
    }

    public NPC getNPC(String id) {
        return npcs.get(id);
    }

    public Collection<NPC> getAllNPCs() {
        return Collections.unmodifiableCollection(npcs.values());
    }

    public NPCRelation getRelation(NPC npc, Player player) {
        int affinity = npc.getAffinity(player.getUniqueId());
        int allyThreshold = plugin.getConfig().getInt("npc.affinity.ally_threshold", 50);
        int hostileThreshold = plugin.getConfig().getInt("npc.affinity.hostile_threshold", -50);
        if (affinity >= allyThreshold) return NPCRelation.ALLIED;
        if (affinity <= hostileThreshold) return NPCRelation.HOSTILE;
        return NPCRelation.NEUTRAL;
    }

    public void adjustAffinity(NPC npc, Player player, int delta) {
        npc.adjustAffinity(player.getUniqueId(), delta);
        NPCRelation relation = getRelation(npc, player);
        if (relation == NPCRelation.ALLIED) {
            String msg = plugin.getConfig().getString("messages.npc_ally_quest_unlock",
                    "§6%npc%이(가) 특별 임무를 맡겼습니다!").replace("%npc%", npc.getName());
            player.sendMessage(msg);
        }
    }

    public void killNPC(NPC npc) {
        npc.die();
        plugin.getServer().broadcastMessage(plugin.getConfig().getString("messages.npc_died",
                "§7%npc%이(가) 명계로 이동했습니다.").replace("%npc%", npc.getName()));
    }

    public void greetPlayer(NPC npc, Player player) {
        NPCRelation relation = getRelation(npc, player);
        String msgKey = switch (relation) {
            case ALLIED -> "messages.npc_greeting_friendly";
            case HOSTILE -> "messages.npc_greeting_hostile";
            default -> "messages.npc_greeting_neutral";
        };
        String msg = plugin.getConfig().getString(msgKey, npc.getName() + ": ...")
                .replace("%npc%", npc.getName())
                .replace("%player%", player.getName());
        player.sendMessage(msg);
    }
}
