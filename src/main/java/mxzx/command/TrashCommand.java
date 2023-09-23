package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import mxzx.abstraccc.BasedCommand;

@CommandAlias("trash")
@Description("Open a Trash")
public class TrashCommand extends BasedCommand {

	@Default
	public void onCommand(Player player) {
		Inventory inv = Bukkit.createInventory(null, 4 * 9, "§cAbfalleimer");
		player.openInventory(inv);
	}
}