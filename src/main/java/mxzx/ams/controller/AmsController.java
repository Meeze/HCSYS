package mxzx.ams.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.abstraccc.Base;
import mxzx.ams.model.Ams;
import mxzx.ams.service.AmsService;
import mxzx.database.DatabaseLocation;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class AmsController implements Base {

    private final AmsService amsService;

    public void placeAms(Player player, Block amsBlock) {
        DatabaseLocation location = DatabaseLocation.builder().x(amsBlock.getX()).y(amsBlock.getY()).z(amsBlock.getZ()).world(amsBlock.getWorld().getName()).build();
        Ams ams = Ams.builder().id(UUID.randomUUID()).owner(playerId(player)).money(0).upgrades(new ArrayList<>()).location(location).build();
        getAmsService().saveAms(ams);
    }

}
