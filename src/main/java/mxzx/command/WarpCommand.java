package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;
import mxzx.warp.controller.WarpController;


@CommandAlias("warp")
@CommandPermission("system.command.warp")
@RequiredArgsConstructor
@Getter
public class WarpCommand extends BasedCommand {

    private final WarpController warpController;

    @Subcommand("create")
    @CommandAlias("setwarp")
    @CommandPermission("system.command.warp.create")
    public void createWarp(Player player, String name) {
        player.sendMessage("creating warp: " + name);
        getWarpController().createWarp(name, player);
    }

    @Subcommand("spawn")
    @CommandAlias("spawn")
    public void warpSpawn(Player player) {
        player.teleport(getWarpController().getWarp("spawn").getLocation());
    }

    @Default
    public void warp(Player player, String name) {
        if(!getWarpController().hasWarp(name)) {
            player.sendMessage("nope");
        }
       player.teleport(getWarpController().getWarp(name).getLocation());
    }


}
