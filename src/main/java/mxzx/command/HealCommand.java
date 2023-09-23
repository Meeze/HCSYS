package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;

@CommandAlias("heal")
@CommandPermission("system.command.heal")
public class HealCommand extends BasedCommand {

    @Syntax("/heal")
    @Default
    public void onCommand(Player player) {
        player.setHealth(player.getMaxHealth());
    }
}
