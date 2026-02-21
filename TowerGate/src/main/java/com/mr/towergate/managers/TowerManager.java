package com.mr.towergate.managers;

import com.mr.towergate.TowerGatePlugin;
import com.mr.towergate.models.TowerFloor;
import org.bukkit.entity.Player;

import java.util.*;

public class TowerManager {

    private final TowerGatePlugin plugin;
    private final Map<UUID, Integer> playerMaxFloor = new HashMap<>();
    private final Map<String, Integer> nationMaxFloor = new HashMap<>();
    private final List<TowerFloor> floors = new ArrayList<>();

    public TowerManager(TowerGatePlugin plugin) {
        this.plugin = plugin;
        initFloors();
    }

    private void initFloors() {
        int maxFloor = plugin.getConfig().getInt("tower.max_floor", 100);
        List<Integer> timeLimitFloors = plugin.getConfig().getIntegerList("tower.special_floors.time_limit");
        List<Integer> forbiddenFloors = plugin.getConfig().getIntegerList("tower.special_floors.forbidden");
        List<Integer> nationCoopFloors = plugin.getConfig().getIntegerList("tower.special_floors.nation_coop");

        for (int i = 1; i <= maxFloor; i++) {
            String theme = getThemeForFloor(i);
            boolean timeLimit = timeLimitFloors.contains(i);
            boolean forbidden = forbiddenFloors.contains(i);
            boolean nationCoop = nationCoopFloors.contains(i);
            floors.add(new TowerFloor(i, theme, timeLimit, forbidden, nationCoop));
        }
    }

    private String getThemeForFloor(int floor) {
        if (floor <= 10) return "생존";
        if (floor <= 20) return "퍼즐";
        if (floor <= 30) return "환경";
        if (floor <= 40) return "혼합";
        if (floor <= 50) return "국가 협력";
        if (floor <= 60) return "심층 생존";
        if (floor <= 70) return "고급 퍼즐";
        if (floor <= 80) return "극한 환경";
        if (floor <= 90) return "탑 보스";
        return "심연의 문";
    }

    public int getPlayerMaxFloor(Player player) {
        return playerMaxFloor.getOrDefault(player.getUniqueId(), 0);
    }

    public void updatePlayerFloor(Player player, int floor) {
        int current = getPlayerMaxFloor(player);
        if (floor > current) {
            playerMaxFloor.put(player.getUniqueId(), floor);
            checkSyncGate(player, floor);
        }
    }

    private void checkSyncGate(Player player, int floor) {
        int syncFloor = plugin.getConfig().getInt("tower.sync_gate_trigger_floor", 50);
        if (floor >= syncFloor) {
            plugin.getServer().broadcastMessage("§b[싱크 게이트] " + player.getName()
                    + "이(가) " + floor + "층에 도달! 글로벌 이벤트 발동 가능!");
        }
    }

    public TowerFloor getFloor(int number) {
        if (number < 1 || number > floors.size()) return null;
        return floors.get(number - 1);
    }

    public List<Map.Entry<UUID, Integer>> getPersonalRankings() {
        List<Map.Entry<UUID, Integer>> rankings = new ArrayList<>(playerMaxFloor.entrySet());
        rankings.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        return rankings;
    }
}
