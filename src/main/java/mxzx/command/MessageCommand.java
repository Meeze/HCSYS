package mxzx.command;

import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;
import mxzx.chat.controller.ReplyController;

import java.util.Arrays;

@CommandAlias("message|msg|tell|whisper|w")
@Getter
@Setter
@RequiredArgsConstructor
public class MessageCommand extends BasedCommand {

    private final ReplyController replyController;

    @Default
    @CommandCompletion("@players message")
    public void onMessage(Player player) {
        player.sendMessage("/msg <Player> <Message>");
    }

    @Subcommand("reply|r")
    @CommandAlias("reply|r")
    public void onMessageReply(Player player, String[] args) {
        Player target = replyController.getLastMessengerMap().get(player);
        if (target == null) {
            player.sendMessage("noone there");
            return;
        }
        target.sendMessage(Arrays.toString(args));
    }

    @CatchUnknown
    @CommandCompletion("@players message")
    public void onMessageToPlayer(Player player, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null) {
            player.sendMessage("noone there");
            return;
        }
        replyController.addLastMessenger(Bukkit.getPlayer(args[0]), player);
        target.sendMessage(Arrays.toString(args));
    }
}
