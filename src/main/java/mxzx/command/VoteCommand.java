package mxzx.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;

@CommandAlias("vote")
@CommandPermission("system.command.vote")
public class VoteCommand extends BaseCommand {

    @Default
    public void onCommand(Player player) {
        player.sendMessage("vote you cucks");
    }

}
