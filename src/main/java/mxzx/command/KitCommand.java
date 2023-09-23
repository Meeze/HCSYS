package mxzx.command;

import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import com.fasterxml.jackson.core.JsonProcessingException;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.abstraccc.BasedCommand;
import mxzx.clan.controller.ClanController;
import mxzx.kit.controller.KitController;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

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
