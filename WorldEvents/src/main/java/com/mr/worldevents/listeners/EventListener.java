package com.mr.worldevents.listeners;

import com.mr.worldevents.WorldEventsPlugin;
import org.bukkit.event.Listener;

public class EventListener implements Listener {

    private final WorldEventsPlugin plugin;

    public EventListener(WorldEventsPlugin plugin) {
        this.plugin = plugin;
    }
}
