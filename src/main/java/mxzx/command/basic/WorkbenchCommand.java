package mxzx.command.basic;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;

@CommandAlias("wb|workbench")
@CommandPermission("system.command.wb")
public class WorkbenchCommand extends BasedCommand {

	@Default
	public void onCommand(Player player) {
		player.openWorkbench(player.getLocation(), true);
	}

}
