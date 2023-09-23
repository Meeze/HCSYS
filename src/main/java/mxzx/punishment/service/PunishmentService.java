package mxzx.punishment.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mxzx.database.Repository;
import mxzx.punishment.model.Punishment;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class PunishmentService {

    private final Repository<Punishment> repository;

    public List<Punishment> loadAll() {
        return getRepository().loadAll();
    }

    public Punishment load(String id) {
        return getRepository().load(id);
    }

    public void save(Punishment punishment) {
        getRepository().save(punishment);
    }

    public void delete(Punishment punish) {
        getRepository().delete(punish);
    }
}
