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
import mxzx._core.BasedCommand;
import mxzx.player.controller.PlayerDataController;

@CommandAlias("stats")
@CommandPermission("system.command.stats")
@RequiredArgsConstructor
@Getter
public class StatsCommand extends BasedCommand {


    private final PlayerDataController playerDataController;

    @CommandCompletion("@players")
    @Syntax("/money <Player>")
    @Default
    public void onCommand(Player player, @Default(value = "") String target) {
        if (target.equalsIgnoreCase("")) {
            long kills = getPlayerDataController().getKills(player.getUniqueId().toString());
            long deaths = getPlayerDataController().getDeaths(player.getUniqueId().toString());
            player.sendMessage("KILLS" + kills);
            player.sendMessage("DEATHS" + deaths);
            player.sendMessage("KD" + kills / deaths);
        } else {
            long money = getPlayerDataController().getMoney(Bukkit.getOfflinePlayer(target).getUniqueId().toString());
            player.sendMessage("money: " + money);
        }
    }

}
