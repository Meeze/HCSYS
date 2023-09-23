package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;

@CommandAlias("enderchest|ec")
@CommandPermission("system.command.enderchest")
public class EnderchestCommand extends BasedCommand {

    @Default
    public void onCommand(Player player) {
        player.openInventory(player.getEnderChest());
    }

    @Syntax("/ec <player>")
    @CommandPermission("system.command.enderchest.others")
    public void onCommand(Player player, String target) {
        player.openInventory(Bukkit.getPlayer(target).getEnderChest());
    }

}