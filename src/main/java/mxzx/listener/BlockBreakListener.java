package mxzx.listener;

import mxzx.metadata.MetadataInfoController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import mxzx.abstraccc.AbstractControllerBasedListener;
import mxzx.abstraccc.ItemBuilder;

import java.util.Random;

@RequiredArgsConstructor
@Getter
public class BlockBreakListener extends AbstractControllerBasedListener {


    private final MetadataInfoController metadataInfoController;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        mineSpawner(event);
    }

    public void mineSpawner(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.MOB_SPAWNER) {
            return;
        }
        String uuid = getMetadataInfoController().getBlockPlacer(event.getBlock());
        //todo global random def
        Random rand = new Random();
        if (uuid.equalsIgnoreCase(event.getPlayer().getUniqueId().toString())) {
            if (rand.nextInt(2) == 0) {
                event.getPlayer().getInventory().addItem(new ItemBuilder().setMaterial(Material.MOB_SPAWNER).setAmount(1).getItem());
            }
        } else if (rand.nextInt(3) == 0) {
                event.getPlayer().getInventory().addItem(new ItemBuilder().setMaterial(Material.MOB_SPAWNER).setAmount(1).getItem());
            }
    }
}
