package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;
import mxzx.admin.Controller.GodmodeController;

@CommandAlias("god|godmode|meze")
@CommandPermission("system.command.god")
@RequiredArgsConstructor
@Getter
public class GodmodeCommand extends BasedCommand {

    private final GodmodeController godmodeController;

    @Syntax("/god")
    @Default
    public void onCommand(Player player, @Default(value = "") String target) {
        if(target.equalsIgnoreCase("")) {
            getGodmodeController().toggleGodmode(player.getUniqueId().toString());
        } else if(player.hasPermission("system.command.fly.others")) {
            Player targetPlayer = Bukkit.getPlayer(target);
            getGodmodeController().toggleGodmode(targetPlayer.getUniqueId().toString());
        }
    }


}
