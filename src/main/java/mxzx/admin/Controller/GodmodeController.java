package mxzx.admin.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class GodmodeController {

    private final List<String> godMap = new ArrayList<>();

    public boolean toggleGodmode(String uuid) {
        if(!godMap.contains(uuid)) {
            godMap.add(uuid);
            return true;
        }
        godMap.remove(uuid);
        return false;
    }

    public boolean isGodMode(Player player) {
        return getGodMap().contains(player.getUniqueId().toString());
    }

}
