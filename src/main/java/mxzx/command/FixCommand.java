package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;

import java.util.Arrays;

@CommandAlias("fix|repair")
@CommandPermission("system.command.fix")
public class FixCommand extends BasedCommand {

    @Syntax("/fix")
    @Default
    public void onCommand(Player player, @Default(value = "") String target) {
        if(target.equalsIgnoreCase("")) {
            fixItems(player);
        } else if(player.hasPermission("system.command.fix.others")) {
            Player targetPlayer = Bukkit.getPlayer(target);
            fixItems(targetPlayer);
        }
    }

    private void fixItems(Player player) {
        player.getInventory().forEach(itemStack -> itemStack.setDurability((short) 0));
        Arrays.stream(player.getInventory().getArmorContents()).forEach(itemStack -> itemStack.setDurability((short) 0));
    }

}
