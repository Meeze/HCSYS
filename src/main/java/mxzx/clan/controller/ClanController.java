package mxzx.clan.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx._core.Base;
import mxzx.clan.model.ClanWarp;
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
public class ClanController implements Base {

    private final ClanService clanService;
    private final Map<String, List<Clan>> clanInvites = new HashMap<>();
    private Map<String, Clan> clans = new HashMap<>();


    private Clan getClan(String tag) {
        getClans().putIfAbsent(tag, getClanService().getClan(tag));
        return getClan(tag);
    }

    private void addAndUpdate(String tag, Clan clan) {
        getClans().put(tag, clan);
        getClanService().updateClan(clan);
    }

    public void createClan(Player player, String tag) {
        ClanMember clanMember = ClanMember.builder().role(ClanRole.ADMIN).id(player.getUniqueId().toString()).build();
        Clan clan = Clan.builder().owner(player.getUniqueId().toString()).tag(tag).members(Collections.singletonList(clanMember)).build();
        getClanService().saveClan(clan);
    }

    public void addMember(Player player, String tag) {
        Clan clan = getClan(tag);
        ClanMember clanMember = ClanMember.builder().role(ClanRole.MEMBER).id(playerId(player)).build();
        clan.getMembers().add(clanMember);
        getClans().put(tag, clan);
        getClanService().updateClan(clan);
    }

    public void addWarp(Player player, String tag, String warp) {
        ClanWarp clanWarp = ClanWarp.builder().location(player.getLocation()).name(warp).build();
        Clan clan = getClan(tag);
        clan.getWarps().add(clanWarp);
        addAndUpdate(tag, clan);
    }

}
