package mxzx.command.basic;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import mxzx._core.BasedCommand;

@CommandAlias("anvil")
@CommandPermission("system.command.anvil")
public class AnvilCommand extends BasedCommand {

	@Default
	public void onCommand(Player player) {
		Inventory inv = Bukkit.createInventory(player, InventoryType.ANVIL);
		player.openInventory(inv);
	}

}
