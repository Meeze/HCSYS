package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;

@CommandAlias("bodysee")
@CommandPermission("system.command.bodysee")
@Getter
public class BodyseeCommand extends BasedCommand {

    @CommandCompletion("@players")
    @Syntax("<player>")
    @Default
    public void onCommand(Player player, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        player.openInventory(targetPlayer.getInventory());
    }

}
