package mxzx.kit.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.kit.model.Kit;
import mxzx.kit.repository.KitRepository;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class KitService {

    private final KitRepository kitRepository;

    public Kit load(String name) {
        return getKitRepository().load(name);
    }

    public void save(Kit kit) {
        getKitRepository().save(kit);
    }

    public List<Kit> loadAll() {
        return getKitRepository().loadAll();
    }

}
