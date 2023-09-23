package mxzx.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import mxzx.abstraccc.AbstractControllerBasedListener;
import mxzx.admin.Controller.GodmodeController;
import mxzx.bounty.controller.BountyController;
import mxzx.player.controller.PlayerDataController;

@RequiredArgsConstructor
@Getter
public class PlayerPvpListener extends AbstractControllerBasedListener {

    private final PlayerDataController playerDataController;
    private final GodmodeController godmodeController;
    private final BountyController bountyController;

    @EventHandler
    public void onPvp(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player)) {
            return;
        }
        if(!(e.getEntity() instanceof Player)) {
            return;
        }
        Player target = (Player) e.getEntity();
        if(getGodmodeController().isGodMode(target)) {
            e.setCancelled(true);
        }
        Player attacker = (Player) e.getDamager();

        if(target.getHealth() - e.getDamage() <= 0) {
            target.sendMessage("you died lul");
            attacker.sendMessage("you killed lul");
            playerDataController.addDeaths(target.getUniqueId().toString(), 1);
            playerDataController.addKills(attacker.getUniqueId().toString(), 1);
            if(!getPlayerDataController().isHunted(target)){
                return;
            }
            getPlayerDataController().addMoney(playerId(attacker), getBountyController().claimBounty(attacker, target));
        }
    }
}
