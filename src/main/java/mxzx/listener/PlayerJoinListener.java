package mxzx.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import mxzx._core.AbstractControllerBasedListener;
import mxzx.player.controller.PlayerDataController;

@RequiredArgsConstructor
@Getter
public class PlayerJoinListener extends AbstractControllerBasedListener {

    private final PlayerDataController playerDataController;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        playerDataController.setUp(e.getPlayer().getUniqueId().toString());
    }


}
