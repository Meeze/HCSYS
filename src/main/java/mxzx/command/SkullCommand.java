package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import mxzx._core.BasedCommand;

@CommandAlias("skull")
@CommandPermission("system.command.skull")
public class SkullCommand extends BasedCommand {

	@Default
	@CommandCompletion("@players")
	public void onSkullCommand(Player player, String target)  {
		ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta im = (SkullMeta) is.getItemMeta();
		im.setOwner(target);
		im.setDisplayName("§7Kopf von: §c" + target);

		is.setItemMeta(im);
		player.getInventory().addItem(is);
		player.updateInventory();
	}
}
