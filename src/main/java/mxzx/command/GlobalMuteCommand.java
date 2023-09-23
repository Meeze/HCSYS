package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;
import mxzx.chat.controller.GlobalMuteController;


@CommandAlias("globalmute|gm")
public class GlobalMuteCommand extends BasedCommand {

    private GlobalMuteController globalMuteController;

    public GlobalMuteCommand(GlobalMuteController globalMuteController) {
        this.globalMuteController = globalMuteController;
    }

    public GlobalMuteController getGlobalMuteController() {
        return globalMuteController;
    }

    @Default
    public void onGlobalMute(Player player) {
        Bukkit.broadcastMessage(player +  "Der Globalmute is momentan: " + globalMuteController.getGlobalMute().isEnabled());
    }

    @Subcommand("on|enable|1")
    public void onGlobalMuteOn(Player player) {
        getGlobalMuteController().getGlobalMute().setEnabled(true);
        Bukkit.broadcastMessage(player + "Der Globalmute wurde eingeschaltet");
    }

    @Subcommand("off|disable|0")
    public void onGlobalMuteOff(Player player) {
        getGlobalMuteController().getGlobalMute().setEnabled(false);
        Bukkit.broadcastMessage(player +  "Der Globalmute wurde ausgeschaltet");
    }
}
