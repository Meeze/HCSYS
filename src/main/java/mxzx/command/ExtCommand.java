package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;

@CommandAlias("ext")
@CommandPermission("system.command.ext")
public class ExtCommand extends BasedCommand {

	@Default
	public void onCommand(Player player) {
		player.setFireTicks(0);
	}

}
