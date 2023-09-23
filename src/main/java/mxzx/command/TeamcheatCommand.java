package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;

@CommandAlias("broadcast|bc")
@CommandPermission("system.staff.teamchat")
public class TeamcheatCommand extends BasedCommand {

    @Syntax("/tc <message...>")
    @Default
    public void onCommand(Player player, String[] args) {
        String message = buildMessageFromArguments(args, 0);
        Bukkit.getOnlinePlayers(). stream().filter(player1 -> player1.hasPermission("system.staff.teamchat")).forEach(player1 -> player1.sendMessage("TC: " + message));
    }

}
