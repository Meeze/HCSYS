package mxzx.clan.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx.clan.model.Clan;
import mxzx.clan.model.ClanMember;
import mxzx.clan.model.ClanRole;
import mxzx.clan.service.ClanService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class ClanController {

    private final ClanService clanService;
    private final Map<String, List<Clan>> clanInvites = new HashMap<>();
    private List<Clan> clans = new ArrayList<>();


    public void createClan(Player player, String name) {
        ClanMember clanMember = ClanMember.builder().role(ClanRole.ADMIN).id(player.getUniqueId().toString()).build();
        Clan clan = Clan.builder().owner(player.getUniqueId().toString()).tag(name).members(Collections.singletonList(clanMember)).build();
        getClanService().saveClan(clan);
    }

}
