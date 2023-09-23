package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;
import mxzx.bounty.controller.BountyController;
import mxzx.player.controller.PlayerDataController;

@RequiredArgsConstructor
@CommandAlias("bounty")
@CommandPermission("system.command.bounty")
@Getter
public class BountyCommand extends BasedCommand {

    private final PlayerDataController playerDataController;
    private final BountyController bountyController;

    @Default
    @CommandCompletion("@players")
    @Syntax("<Player> <amount>")
    public void onCommand(Player player, String target, long amount) {
        getPlayerDataController().removeMoney(player.getUniqueId().toString(), amount);
        getPlayerDataController().setHunted(namePlayer(target), true);
        getBountyController().createBounty(player, namePlayer(target), amount);
    }

}
