package mxzx._core;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.RegisteredCommand;
import com.google.common.collect.SetMultimap;
import org.bukkit.entity.Player;

public abstract class BasedCommand extends BaseCommand implements Base {

    public void showHelp(Player player) {
        SetMultimap<String, RegisteredCommand> map = getSubCommands();
        map.asMap().forEach((s, registeredCommands) -> {
            player.sendMessage(s + " :");
            ;
            registeredCommands.forEach(registeredCommand -> {
                player.sendMessage(registeredCommand.getCommand() + " | " + registeredCommand.getSyntaxText());
                player.sendMessage(registeredCommand.getHelpText() + " | " + registeredCommand.getRequiredPermissions());
            });
        });
    }

}
