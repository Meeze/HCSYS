package mxzx.command;

import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.abstraccc.BasedCommand;
import mxzx.ams.controller.AmsController;
import mxzx.perk.controller.PerkController;
import mxzx.perk.model.PerkEffect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("perk|perks")
@CommandPermission("system.command.perk")
@RequiredArgsConstructor
@Getter
public class PerkCommand extends BasedCommand {

	private final PerkController perkController;

	@Subcommand("create")
	@Syntax("name effect cost")
	public void onCommand(Player player, String name, PerkEffect perkEffect, long cost) {
		getPerkController().createPerk(name, perkEffect, cost);
	}

	@Subcommand("activate|on")
	public void activatePerk(Player player, String name) {
		player.sendMessage(getPerkController().getPerkService().getPerk(name).getPerkEffect().name());
	}

	@Subcommand("list")
	@Default
	public void listPerks(Player player) {
		getPerkController().getPerks().forEach((s, perk) -> player.sendMessage(s));
	}

}
