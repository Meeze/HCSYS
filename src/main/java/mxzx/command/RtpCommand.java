package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;

@CommandAlias("rtp|randomtp")
@CommandPermission("system.command.rtp")
public class RtpCommand extends BasedCommand {

	@Default
	public void onCommand(Player player) {
		player.sendMessage("rtp");
	}

}
