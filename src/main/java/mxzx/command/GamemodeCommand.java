package mxzx.command;

import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;

@CommandAlias("gamemode|gm")
@CommandPermission("system.command.gamemode")
public class GamemodeCommand extends BasedCommand {

    @CommandCompletion("@players")
    @CommandAlias("0")
    @Subcommand("0|s|survival|")
    @Description("Short for survival")
    @CommandPermission("system.gamemode.survival")
    public void onCommand1(Player player, @Default(value = "0") String gm, @Default(value = " ") String target) {
        onCommandSchema(player, target, GameMode.SURVIVAL);
    }

    @CommandAlias("1")
    @Subcommand("1|c|creative|")
    @Description("Short for creative")
    @CommandPermission("system.gamemode.creative")
    @CommandCompletion("@players")
    public void onCommand2(Player player, @Default(value = "1") String gm, @Default(value = " ") String target) {
        onCommandSchema(player, target, GameMode.CREATIVE);
    }

    @CommandAlias("2")
    @Subcommand("2|a|adventure|")
    @Description("Short for adventure")
    @CommandPermission("system.gamemode.adventure")
    @CommandCompletion("@players")
    public void onCommand(Player player, @Default(value = "2") String gm, @Default(value = " ") String target) {
        onCommandSchema(player, target, GameMode.ADVENTURE);
    }

    @CommandAlias("3")
    @Subcommand("3|spec|spectator|")
    @Description("Short for spectator")
    @CommandCompletion("@players")
    public void onCommand4(Player player, @Default(value = "3") String gm, @Default(value = " ") String target) {
        onCommandSchema(player, target, GameMode.SPECTATOR);
    }

    public void onCommandSchema(Player player, String target, GameMode gameMode) {
        Player giver = player;
        Player receiver = getTarget(player, target);
        if (receiver.getUniqueId() != giver.getUniqueId())
            giver.sendMessage("gave " + receiver.getName() + " " + gameMode.name());
        player.setGameMode(gameMode);
        receiver.sendMessage(gameMode.name());
    }


    @HelpCommand
    @CatchUnknown
    @Default
    @Subcommand("help")
    @Syntax("/gamemode 0-3 <target>")
    public void onCommandHelp(Player player) {
      showHelp(player);
    }

    public Player getTarget(Player player, String targetName) {
        return targetName.length() >= 2 ? Bukkit.getPlayer(targetName) : player;
    }

}