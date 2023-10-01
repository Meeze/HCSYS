package mxzx.command.economy;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx._core.BasedCommand;
import mxzx.kit.controller.KitController;
import org.bukkit.entity.Player;

import java.io.IOException;

@CommandAlias("kit")
@CommandPermission("system.command.god")
@RequiredArgsConstructor
@Getter
public class KitCommand extends BasedCommand {

    private final KitController kitController;

    @Subcommand("give")
    @Syntax("name")
    @CommandPermission("system.command.kit")
    public void onKit(Player player, String name) throws IOException {
        getKitController().giveKit(player, name);
    }

    @Syntax("name")
    @Subcommand("create")
    @CommandPermission("system.command.kit.create")
    public void onCreate(Player player, String name) {
        getKitController().createKit(player, name, player.getInventory());
    }

}
