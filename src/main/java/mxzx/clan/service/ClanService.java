package mxzx.clan.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.clan.model.Clan;
import mxzx.database.Repository;

@RequiredArgsConstructor
@Getter
public class ClanService {

    private final Repository<Clan> clanRepository;

    public void saveClan(Clan clan) {
        getClanRepository().save(clan);
    }

}
