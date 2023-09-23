package mxzx.teleport.controller;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@Getter
public class TeleportController {

    private final Map<String, Location> lastLocations = new HashMap<>();

    public void setLastLocation(Player player) {
        getLastLocations().put(player.getUniqueId().toString(), player.getLocation());
    }

    public boolean hasLastLocation(Player player) {
        return getLastLocations().containsKey(player.getUniqueId().toString());
    }

    public Location getLastLocation(Player player) {
        return getLastLocations().get(player.getUniqueId().toString());
    }

    public void teleportBack(Player player) {
        player.teleport(getLastLocation(player));
    }

}
