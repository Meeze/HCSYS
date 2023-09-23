package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;

import java.util.Set;


@CommandAlias("break|destroy")
@CommandPermission("system.command.break")
public class BreakCommand extends BasedCommand {

    @Default
    public void onBreakCommand(Player player)  {
        Block block = player.getTargetBlock((Set<Material>) null, 15);
        block.setType(Material.AIR);
    }
}
