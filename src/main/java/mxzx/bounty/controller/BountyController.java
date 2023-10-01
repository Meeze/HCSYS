package mxzx.bounty.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx._core.Base;
import mxzx.bounty.model.Bounty;
import mxzx.bounty.service.BountyService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class BountyController implements Base {

    private final BountyService bountyService;
    private final Map<String, Long> bountyCache = new HashMap<>();

    public long countBounty(Player player, Player target) {
        getBountyCache().putIfAbsent(playerId(target), getBountyService().loadByPlayer(target).stream().mapToLong(Bounty::getAmount).sum());
        return getBountyCache().size();
    }

    public void createBounty(Player player, Player target, long amount) {
        Bounty bounty = Bounty.builder().issuer(playerId(player)).target(playerId(target)).amount(amount).build();
        getBountyService().save(bounty);
    }

    public long claimBounty(Player player, Player target) {
        long bounty = countBounty(player, target);
        getBountyCache().remove(playerId(player));
        return bounty;
    }

}
