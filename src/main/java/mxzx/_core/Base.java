package mxzx._core;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.Instant;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface Base {

    default boolean invalidBlock(Block b) {
        return Objects.isNull(b) || b.getType() == Material.AIR;
    }

    default ItemStack[] itemArr(ItemStack itemStack) {
        return Collections.singletonList(itemStack).toArray(new ItemStack[0]);
    }

    default String uuidSafe(String name) {
        return Bukkit.getPlayer(name).isOnline() ? Bukkit.getPlayer(name).getUniqueId().toString() : Bukkit.getOfflinePlayer(name).toString();
    }

    default String buildMessageFromArguments(String[] args, int skipAmount) {
        String builderOutput;
        if (args.length == 1) {
            builderOutput = args[0];
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = skipAmount; i < args.length; i++) {
                builder.append(args[i]);
                if (i != args.length - 1) {
                    builder.append(" ");
                }
            }
            builderOutput = builder.toString();
        }
        return builderOutput;
    }

    default Player getTarget(Player player, String targetName) {
        return targetName.length() >= 2 ? getPlayerAsValidTarget(player, targetName).orElseGet(() -> player) : player;
    }

    default Optional<Player> getPlayerAsValidTarget(Player self, String targetName) {
        Player p = Bukkit.getPlayer(targetName);
        return Optional.of(p);
    }

    default OfflinePlayer getOfflinePlayerFromUUID(String uuid) {
        if (uuid.isEmpty()) {
            return null;
        }
        return Bukkit.getOfflinePlayer(UUID.fromString(uuid));
    }

    default String getPlayerName(String uuid) {
        if (Bukkit.getServer().getOnlinePlayers().stream().anyMatch(player -> player.getUniqueId().toString().equalsIgnoreCase(uuid))) {
            return Bukkit.getPlayer(UUID.fromString(uuid)).getName();
        } else {
            return (getOfflinePlayerFromUUID(uuid) != null) ? getOfflinePlayerFromUUID(uuid).getName() : "<?>";
        }
    }

    default String getDateString(long seconds) {
        return seconds > -1 ? Instant.ofEpochSecond(seconds).toString() : "<?>";
    }

    default String playerId(Player player) {
        return player.getUniqueId().toString();
    }

    default Player namePlayer(String name) {
        return Bukkit.getPlayer(name);
    }

    default UUID uuid(String id) {
        return UUID.fromString(id);
    }

    default Player uuidPlayer(String uuid) {
        return Bukkit.getPlayer(UUID.fromString(uuid));
    }


}
