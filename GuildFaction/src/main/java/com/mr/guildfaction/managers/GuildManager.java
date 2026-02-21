package com.mr.guildfaction.managers;

import com.mr.guildfaction.GuildFactionPlugin;
import com.mr.guildfaction.models.Guild;
import org.bukkit.entity.Player;

import java.util.*;

public class GuildManager {

    private final GuildFactionPlugin plugin;
    private final Map<String, Guild> guilds = new HashMap<>();
    private final Map<UUID, String> playerGuildMap = new HashMap<>();

    public GuildManager(GuildFactionPlugin plugin) {
        this.plugin = plugin;
    }

    public Guild createGuild(Player leader, String name) {
        String id = name.toLowerCase().replace(" ", "_") + "_" + System.currentTimeMillis();
        Guild guild = new Guild(id, name, leader.getUniqueId());
        guilds.put(id, guild);
        playerGuildMap.put(leader.getUniqueId(), id);
        return guild;
    }

    public Guild getPlayerGuild(Player player) {
        String guildId = playerGuildMap.get(player.getUniqueId());
        return guildId != null ? guilds.get(guildId) : null;
    }

    public boolean isInGuild(Player player) {
        return playerGuildMap.containsKey(player.getUniqueId());
    }

    public boolean joinGuild(Player player, Guild guild) {
        int maxMembers = plugin.getConfig().getInt("guild.max_members", 50);
        if (guild.getMemberCount() >= maxMembers) return false;
        guild.addMember(player.getUniqueId());
        playerGuildMap.put(player.getUniqueId(), guild.getId());
        return true;
    }

    public void leaveGuild(Player player) {
        Guild guild = getPlayerGuild(player);
        if (guild == null) return;
        guild.removeMember(player.getUniqueId());
        playerGuildMap.remove(player.getUniqueId());
    }

    public void disbandGuild(Guild guild) {
        for (UUID member : guild.getMembers().keySet()) {
            playerGuildMap.remove(member);
        }
        guilds.remove(guild.getId());
    }

    public Guild getGuildByName(String name) {
        return guilds.values().stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public Collection<Guild> getAllGuilds() {
        return Collections.unmodifiableCollection(guilds.values());
    }
}
