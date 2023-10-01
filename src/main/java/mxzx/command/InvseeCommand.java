package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;

@CommandAlias("invsee")
@CommandPermission("system.command.invsee")
@Getter
public class InvseeCommand extends BasedCommand {

    @CommandCompletion("@players")
    @Syntax("<player>")
    @Default
    public void onCommand(Player player, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        player.openInventory(targetPlayer.getInventory());
    }


}
