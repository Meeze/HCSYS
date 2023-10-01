package mxzx.command.economy;

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

@RequiredArgsConstructor
@CommandAlias("pay")
@CommandPermission("system.command.pay")
@Getter
public class PayCommand extends BasedCommand {

    private final PlayerDataController playerDataController;

    @Default
    @CommandCompletion("@players")
    @Syntax("<Player> <amount>")
    public void onCommand(Player player, String target, long amount) {
        playerDataController.addMoney(Bukkit.getOfflinePlayer(target).getUniqueId().toString(), amount);
        playerDataController.removeMoney(player.getUniqueId().toString(), amount);
        player.sendMessage(playerDataController.getMoney(playerId(player)) + "now");
    }

}
