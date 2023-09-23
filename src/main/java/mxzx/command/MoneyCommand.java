package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;
import mxzx.player.controller.PlayerDataController;

@CommandAlias("money")
@CommandPermission("system.command.money")
@RequiredArgsConstructor
@Getter
public class MoneyCommand extends BasedCommand {

    private final PlayerDataController playerDataController;

    @CommandCompletion("@players")
    @Syntax("/money <Player>")
    @Default
    public void onCommand(Player player, @Default(value = "") String target) {
        if(target.equalsIgnoreCase("")) {
            long money = getPlayerDataController().getMoney(player.getUniqueId().toString());
            player.sendMessage("money" + money);
        } else {
            long money = getPlayerDataController().getMoney(Bukkit.getOfflinePlayer(target).getUniqueId().toString());
            player.sendMessage("money: " + money);
        }
    }
}
