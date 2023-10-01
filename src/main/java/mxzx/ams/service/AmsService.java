package mxzx.ams.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx._core.Base;
import mxzx._core.BaseService;
import mxzx.ams.model.Ams;
import mxzx.ams.repository.AmsRepository;
import mxzx.database.Repository;
import org.bukkit.Location;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AmsService implements BaseService<Ams> {

    private final AmsRepository amsRepository;

    public Ams loadByLocation(Location location) {
        return getAmsRepository().loadByLocation(location);
    }

    @Override
    public void save(Ams type) {
        getAmsRepository().save(type);
    }

    @Override
    public void update(Ams type) {
        getAmsRepository().update(type);
    }

    @Override
    public Ams load(String id) {
        return getAmsRepository().load(id);
    }

    @Override
    public List<Ams> loadAll() {
        return getAmsRepository().loadAll();
    }

    @Override
    public void delete(Ams type) {
        getAmsRepository().delete(type);
    }
}
