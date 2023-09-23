package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.abstraccc.BasedCommand;
import mxzx.crate.controller.CrateController;
import mxzx.crate.model.CrateRarity;
import org.bukkit.entity.Player;

@CommandAlias("crate|crates")
@CommandPermission("system.command.crate")
@RequiredArgsConstructor
@Getter
public class CrateCommand extends BasedCommand {

    private final CrateController crateController;

    @Subcommand("create")
    public void onCreate(Player player, String name, CrateRarity rarity, long cost) {
        getCrateController().createCrate(name, rarity, cost);
    }

    @Subcommand("add")
    public void onAdd(Player player, String crate, String itemName, int tickets, int weight) {
        getCrateController().addItemToCrate(crate, itemName, player.getItemInHand(), tickets, weight);
    }

    @Subcommand("list")
    @Default
    public void listPerks(Player player) {
        getCrateController().getCrates().forEach((s, perk) -> player.sendMessage(s));
    }

}
