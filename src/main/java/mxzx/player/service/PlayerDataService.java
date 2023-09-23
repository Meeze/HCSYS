package mxzx.player.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import mxzx.database.Repository;
import mxzx.player.model.PlayerData;

import java.util.List;

@RequiredArgsConstructor
@Data
public class PlayerDataService {

    private final Repository<PlayerData> repository;

    public List<PlayerData> loadAll() {
        return getRepository().loadAll();
    }

    public PlayerData load(String id) {
        return getRepository().load(id);
    }

    public void save(PlayerData toSave) {
        getRepository().save(toSave);
    }

    public void delete(PlayerData toDelete) {
        getRepository().delete(toDelete);
    }

}
