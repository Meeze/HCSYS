package mxzx.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import mxzx.metadata.controller.MetadataInfoController;
import org.bukkit.event.block.BlockPlaceEvent;
import mxzx._core.AbstractControllerBasedListener;

@RequiredArgsConstructor
@Getter
public class BlockPlaceListener extends AbstractControllerBasedListener {

    private final MetadataInfoController metadataInfoController;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        appendMetadata(e);
    }

    public void appendMetadata(BlockPlaceEvent e) {
        getMetadataInfoController().appendBlockHistory(e.getBlock(), e.getPlayer().getUniqueId().toString());
    }

}
