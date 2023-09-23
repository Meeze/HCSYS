package mxzx.metadata;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.metadata.FixedMetadataValue;
import mxzx.Main;
import mxzx.abstraccc.BaseController;

import java.time.Instant;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Getter
public class MetadataInfoController implements BaseController {

    private final Main main;

    public boolean isContainerBlock(Block b) {
        return b instanceof InventoryHolder;
    }

    public List<Map.Entry<String, Long>> getOpeners(Block b) {
        List<Map.Entry<String, Long>> openerList = new ArrayList<>();
        if(!b.hasMetadata("opened")) {
            return openerList;
        }
        String uuidCsv = b.getMetadata("opened").get(0).asString();
        for (String uuidAndDate : uuidCsv.split(";")) {
            String[] keyValue = uuidAndDate.split(":");
            Map.Entry<String, Long> entry = new AbstractMap.SimpleEntry<String, Long>(keyValue[0], Long.parseLong(keyValue[1]));
            openerList.add(entry);
        }
        return openerList;
    }

    public String getBlockPlacer(Block b) {
        return b.hasMetadata("placedBy") ? b.getMetadata("placedBy").get(0).asString() : "";
    }

    public long getBlockPlacementDate(Block b) {
        return b.hasMetadata("placedOn") ? b.getMetadata("placedOn").get(0).asLong() : 0;
    }

    public void appendMetadata(Block b, String key, String value) {
        b.setMetadata(key, new FixedMetadataValue(getMain(), value));
    }

    public void appendContainerHistory(BlockState state, String uuid) {
        String entry = uuid + ":" + Instant.now().getEpochSecond();
        appendMetadata(state.getBlock(), "opened", entry);
    }

    public void appendBlockHistory(Block b, String uuid) {
        long date = Instant.now().getEpochSecond();
        appendMetadata(b, "placedBy", uuid);
        appendMetadata(b, "placedOn", Long.toString(date) );
    }

}
