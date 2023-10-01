package mxzx.command.team;

import co.aikar.commands.annotation.Default;
import mxzx._core.BasedCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("broadcast|bc")
@CommandPermission("system.command.bc")
public class BroadcastCommand extends BasedCommand {

    @Syntax("/broadcast <message...>")
    @Default
    public void onCommand(Player player, String[] args) {
        String message = buildMessageFromArguments(args, 0);
        Bukkit.broadcastMessage("BC: " + message);
    }

}
