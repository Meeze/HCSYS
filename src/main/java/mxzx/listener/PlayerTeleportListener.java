package mxzx.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerTeleportEvent;
import mxzx.abstraccc.AbstractControllerBasedListener;
import mxzx.teleport.controller.TeleportController;

@RequiredArgsConstructor
@Getter
public class PlayerTeleportListener extends AbstractControllerBasedListener {

    private final TeleportController teleportController;

    @EventHandler
        public void onPlayerTeleport(PlayerTeleportEvent e) {
        getTeleportController().setLastLocation(e.getPlayer());
    }


}
