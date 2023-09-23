package mxzx.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;

@CommandAlias("perks")
@CommandPermission("system.command.perks")
public class PerksCommand extends BaseCommand {

	@Default
	public void onCommand(Player player) {
		player.sendMessage("perks");
	}

}
