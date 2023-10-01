package mxzx.command.basic;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;

@CommandAlias("fly|bird")
@CommandPermission("system.command.fly")
public class FlyCommand extends BasedCommand {

    @Syntax("/fly")
    @Default
    public void onCommand(Player player, @Default(value = "") String target) {
        if(target.equalsIgnoreCase("")) {
            toggleFly(player);
        } else if(player.hasPermission("system.command.fly.others")) {
            Player targetPlayer = Bukkit.getPlayer(target);
            toggleFly(targetPlayer);
        }
    }

    private void toggleFly(Player player) {
        if(player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        player.setFlying(!player.isFlying());
    }

}
