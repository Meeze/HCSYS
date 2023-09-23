package mxzx.command;

import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BasedCommand;
import mxzx.chat.controller.ReplyController;

@CommandAlias("relore")
@Getter
@Setter
@RequiredArgsConstructor
public class ReloreCommand extends BasedCommand {

    private final ReplyController replyController;

    @Default
    public void onRelore(Player player, int line, String[] args) {
        player.sendMessage(line + "int");
        player.sendMessage(args);
    }

    @CatchUnknown
    public void onMessageToPlayer(Player player, String[] args) {
        showHelp(player);
    }
}
