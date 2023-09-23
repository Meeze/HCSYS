package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import mxzx.abstraccc.BasedCommand;

@CommandAlias("more")
@CommandPermission("system.command.more")
public class MoreCommand extends BasedCommand {

	@Default
	public void onCommand(Player player) {
		ItemStack item = player.getItemInHand();
		item.setAmount(64);
	}
}