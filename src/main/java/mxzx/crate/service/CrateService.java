package mxzx.crate.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.crate.model.Crate;
import mxzx.crate.repository.CrateRepository;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class CrateService {

    private final CrateRepository crateRepository;


    public void saveCrate(Crate crate) {
        getCrateRepository().save(crate);
    }

    public Crate loadCrate(String name) {
        return getCrateRepository().load(name);
    }

    public List<Crate> loadAll() {
        return getCrateRepository().loadAll();
    }

}
