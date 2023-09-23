package mxzx.perk.controller;

import lombok.Data;
import lombok.Getter;
import mxzx.perk.model.Perk;
import mxzx.perk.model.PerkEffect;
import mxzx.perk.service.PerkService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Data
public class PerkController {

    private final PerkService perkService;
    private final Map<String, Perk> perks = new HashMap<>();

    private void addPerk(Perk perk) {
        getPerks().put(perk.getName(), perk);
    }

    public void setUp() {
        loadAll().forEach(this::addPerk);
    }

    public void createPerk(String name, PerkEffect effect, long cost) {
        Perk perk = Perk.builder().name(name).perkEffect(effect).cost(cost).build();
        addPerk(perk);
        getPerkService().save(perk);
    }

    public List<Perk> loadAll() {
        return getPerkService().loadAll();
    }

}
