package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;

@CommandAlias("feed")
@CommandPermission("system.command.feed")
public class FeedCommand extends BasedCommand {

    @CommandCompletion("@players")
    @Syntax("/feed <Player>")
    @Default
    public void onCommand(Player player, @Default(value = "") String target) {
        if(target.equalsIgnoreCase("")) {
            player.setFoodLevel(20);
            player.setSaturation(20);
        } else {
            Player targetPlayer = Bukkit.getPlayer(target);
            targetPlayer.setFoodLevel(20);
            targetPlayer.setSaturation(20);
        }
    }
}
