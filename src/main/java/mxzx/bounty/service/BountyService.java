package mxzx.bounty.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx.bounty.model.Bounty;
import mxzx.bounty.repository.BountyRepository;

import java.util.List;

@RequiredArgsConstructor
@Data
public class BountyService {

    private final BountyRepository repository;

    public List<Bounty> loadAll() {
        return getRepository().loadAll();
    }

    public List<Bounty> loadByPlayer(Player player) {
        return getRepository().loadByHunted(player.getUniqueId().toString());
    }

    public Bounty load(String id) {
        return getRepository().load(id);
    }

    public void save(Bounty toSave) {
        getRepository().save(toSave);
    }

    public void delete(Bounty toDelete) {
        getRepository().delete(toDelete);
    }

}
