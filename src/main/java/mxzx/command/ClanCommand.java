package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;
import mxzx.clan.controller.ClanController;

@CommandAlias("clan|c")
@CommandPermission("system.command.clan")
@RequiredArgsConstructor
@Getter
public class ClanCommand extends BasedCommand {

    private final ClanController clanController;

    @Default
    public void onCommand(Player player) {
        showHelp(player);
    }

    @Syntax("create name")
    @Subcommand("create|erstellen")
    @CommandPermission("system.command.enderchest.others")
    public void onCommand(Player player, String name) {
       getClanController().createClan(player, name);
    }

}