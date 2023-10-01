package mxzx.clan.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.clan.model.Clan;
import mxzx.database.Repository;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ClanService {

    private final Repository<Clan> clanRepository;

    public void saveClan(Clan clan) {
        getClanRepository().save(clan);
    }

    public void updateClan(Clan clan) {
        getClanRepository().update(clan);
    }

    public Clan getClan(String tag) {
        return getClanRepository().load(tag);
    }

    public List<Clan> loadAll() {
        return getClanRepository().loadAll();
    }



}
