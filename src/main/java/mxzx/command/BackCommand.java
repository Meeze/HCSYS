package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;
import mxzx.teleport.controller.TeleportController;

@CommandAlias("back")
@CommandPermission("system.command.back")
@RequiredArgsConstructor
@Getter
public class BackCommand extends BasedCommand {

	private final TeleportController teleportController;

	@Default
	public void onCommand(Player player) {
		getTeleportController().teleportBack(player);
	}

}
