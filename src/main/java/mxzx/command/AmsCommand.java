package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx._core.BasedCommand;
import mxzx.ams.controller.AmsController;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("ams")
@CommandPermission("system.command.ams")
@RequiredArgsConstructor
@Getter
public class AmsCommand extends BasedCommand {

	private final AmsController amsController;

	@Default
	public void onCommand(Player player) {
		getAmsController().placeAms(player, Bukkit.getWorld(player.getWorld().getUID()).getBlockAt(player.getLocation().subtract(0, 1, 0)));
	}

}
