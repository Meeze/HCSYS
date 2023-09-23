package mxzx.perk.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.perk.model.Perk;
import mxzx.perk.repository.PerkRepository;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class PerkService {

    private final PerkRepository perkRepository;

    public void save(Perk perk) {
        getPerkRepository().save(perk);
    }

    public List<Perk> loadAll() {
        return getPerkRepository().loadAll();
    }

    public Perk getPerk(String name) {
        return getPerkRepository().load(name);
    }

    public void deletePerk(Perk perk) {
        getPerkRepository().delete(perk);
    }


}
