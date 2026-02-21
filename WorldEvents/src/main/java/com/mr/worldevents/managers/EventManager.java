package com.mr.worldevents.managers;

import com.mr.worldevents.WorldEventsPlugin;
import com.mr.worldevents.models.WorldEvent;
import org.bukkit.Bukkit;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class EventManager {

    private final WorldEventsPlugin plugin;
    private final Set<WorldEvent> activeEvents = EnumSet.noneOf(WorldEvent.class);
    private final Map<WorldEvent, Long> eventEndTimes = new EnumMap<>(WorldEvent.class);

    public EventManager(WorldEventsPlugin plugin) {
        this.plugin = plugin;
    }

    public void triggerEvent(WorldEvent event) {
        if (activeEvents.contains(event)) return;
        activeEvents.add(event);

        String msg = plugin.getConfig().getString("messages.event_start",
                "§6§l[월드 이벤트] §e%event%§6이(가)시작되었습니다!")
                .replace("%event%", event.getKoreanName());
        Bukkit.broadcastMessage(msg);

        broadcastEventDetails(event);

        // 이벤트 종료 스케줄
        long durationTicks = getEventDurationTicks(event);
        if (durationTicks > 0) {
            eventEndTimes.put(event, System.currentTimeMillis() + (durationTicks * 50));
            Bukkit.getScheduler().runTaskLater(plugin, () -> endEvent(event), durationTicks);
        }
    }

    private void broadcastEventDetails(WorldEvent event) {
        String detailKey = switch (event) {
            case DEMON_EROSION_WEEK -> "messages.demon_erosion_warning";
            case HEAVEN_RELIC_DESCENT -> "messages.heaven_descent_warning";
            case DEMON_GATE_OPEN -> "messages.demon_gate_warning";
            case SYNC_GATE -> "messages.sync_gate_warning";
        };
        String detail = plugin.getConfig().getString(detailKey, event.getDescription());
        Bukkit.broadcastMessage(detail);
    }

    private long getEventDurationTicks(WorldEvent event) {
        String key = switch (event) {
            case DEMON_EROSION_WEEK -> "events.demon_erosion_week.duration_hours";
            case HEAVEN_RELIC_DESCENT -> "events.heaven_relic_descent.duration_hours";
            case DEMON_GATE_OPEN -> "events.demon_gate_open.duration_hours";
            case SYNC_GATE -> -1;
        };
        if (key.equals("-1")) return -1L;
        long hours = plugin.getConfig().getLong(key, 24L);
        return hours * 3600 * 20;
    }

    public void endEvent(WorldEvent event) {
        if (!activeEvents.contains(event)) return;
        activeEvents.remove(event);
        eventEndTimes.remove(event);
        String msg = plugin.getConfig().getString("messages.event_end",
                "§7[월드 이벤트] §e%event%§7이(가) 종료되었습니다.")
                .replace("%event%", event.getKoreanName());
        Bukkit.broadcastMessage(msg);
    }

    public boolean isActive(WorldEvent event) {
        return activeEvents.contains(event);
    }

    public Set<WorldEvent> getActiveEvents() {
        return java.util.Collections.unmodifiableSet(activeEvents);
    }
}
