package mxzx.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import mxzx.metadata.MetadataInfoController;
import mxzx.abstraccc.BasedCommand;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@CommandAlias("blockhistory|blockinfo|inspect|metadata")
@CommandPermission("system.command.blockhistory")
public class BlockInfoCommand extends BasedCommand {

    private final MetadataInfoController metadataInfoController;

    private void printMetadata(Player p, String placedBy, long placedOn, List<Map.Entry<String, Long>> history, boolean container) {
        p.sendMessage("§8×§r §c--<Block-Daten>--");
        p.sendMessage("§7Spieler: §c" + placedBy);
        p.sendMessage("§7Datum: §c" + placedOn);
        p.sendMessage("§7Typ: §c" + (container ? "container" : "block"));
        if (container) {
            p.sendMessage("§8×§r §c--<Inventar-Zugriffe>--");
            for (Map.Entry<String, Long> entry : history) {
                p.sendMessage("§7Spieler: §c" + entry.getKey());
                p.sendMessage("§7Datum: §c" + entry.getValue());
            }
        }
    }

    @Default
    @Syntax("/blockhistory")
    public boolean onCommand(Player player, String[] strings) {
        Block b = player.getTargetBlock((HashSet<Byte>) null, 5);
        if (b == null) {
            player.sendMessage("§cBitte schaue einen Block an.");
            return false;
        }
        String placedBy = getMetadataInfoController().getBlockPlacer(b);
        long placedOn = getMetadataInfoController().getBlockPlacementDate(b);
        List<Map.Entry<String, Long>> history = getMetadataInfoController().getOpeners(b);
        boolean container = getMetadataInfoController().isContainerBlock(b);
        printMetadata(player, placedBy, placedOn, history, container);
        return true;
    }

}