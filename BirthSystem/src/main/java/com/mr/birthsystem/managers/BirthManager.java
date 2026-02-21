package com.mr.birthsystem.managers;

import com.mr.birthsystem.BirthSystemPlugin;
import com.mr.birthsystem.models.Race;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;

public class BirthManager {

    private final BirthSystemPlugin plugin;
    private final Random random = new Random();
    private final Set<UUID> bornPlayers = new HashSet<>();
    private final Map<UUID, String> playerBirthDimensions = new HashMap<>();
    private final Map<UUID, Race> playerRaces = new HashMap<>();

    public BirthManager(BirthSystemPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean hasBeenBorn(Player player) {
        return bornPlayers.contains(player.getUniqueId());
    }

    public String assignBirthDimension(Player player) {
        if (hasBeenBorn(player)) {
            player.sendMessage(plugin.getConfig().getString("messages.already_born", "§c이미 출생이 완료되었습니다."));
            return playerBirthDimensions.get(player.getUniqueId());
        }

        ConfigurationSection dimSection = plugin.getConfig().getConfigurationSection("birth.dimensions");
        List<String> dims = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();

        if (dimSection != null) {
            for (String key : dimSection.getKeys(false)) {
                dims.add(key);
                weights.add(dimSection.getInt(key));
            }
        }

        int totalWeight = weights.stream().mapToInt(Integer::intValue).sum();
        int roll = random.nextInt(totalWeight);
        int cumulative = 0;
        String selected = "fantasy";

        for (int i = 0; i < dims.size(); i++) {
            cumulative += weights.get(i);
            if (roll < cumulative) {
                selected = dims.get(i);
                break;
            }
        }

        playerBirthDimensions.put(player.getUniqueId(), selected);
        Race race = assignRace(selected);
        playerRaces.put(player.getUniqueId(), race);
        bornPlayers.add(player.getUniqueId());

        String raceName = plugin.getConfig().getString("races." + selected + ".name", race.getKoreanName());
        String msg = plugin.getConfig().getString("messages.birth_assigned",
                "§a당신은 §e%dimension% §a차원에서 태어났습니다! 종족: §b%race%")
                .replace("%dimension%", selected)
                .replace("%race%", raceName);
        player.sendMessage(msg);

        plugin.getLogger().info(player.getName() + " -> 차원: " + selected + ", 종족: " + race.getKoreanName());
        return selected;
    }

    private Race assignRace(String dimension) {
        return switch (dimension) {
            case "underworld" -> Race.DEMON;
            case "heaven" -> Race.ANGEL;
            case "immortal" -> Race.IMMORTAL;
            case "demon" -> Race.YOKAI;
            case "netherworld" -> Race.SPIRIT_BODY;
            case "spirit" -> Race.SPIRIT_KIN;
            default -> Race.HUMAN;
        };
    }

    public Race getPlayerRace(Player player) {
        return playerRaces.getOrDefault(player.getUniqueId(), Race.HUMAN);
    }

    public String getPlayerBirthDimension(Player player) {
        return playerBirthDimensions.getOrDefault(player.getUniqueId(), "fantasy");
    }

    public boolean hasNoDeathDrop(Player player) {
        String dim = getPlayerBirthDimension(player);
        return plugin.getConfig().getBoolean("races." + dim + ".no_death_drop", false);
    }

    public double getGrowthMultiplier(Player player) {
        String dim = getPlayerBirthDimension(player);
        return plugin.getConfig().getDouble("races." + dim + ".growth_multiplier", 1.0);
    }
}
