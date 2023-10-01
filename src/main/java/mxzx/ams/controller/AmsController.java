package mxzx.ams.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx._core.Base;
import mxzx.ams.model.Ams;
import mxzx.ams.service.AmsService;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.HashMap;

@RequiredArgsConstructor
@Getter
public class AmsController implements Base {

    private final AmsService amsService;
    private final int baseSpawnerRate = 5;
    private final int baseMulti = 1;
    private HashMap<Location, Ams> ams = new HashMap<>();


    public void placeAms(Player player, Location location) {
        Ams ams = Ams.builder().owner(playerId(player)).baseSpawnerRate(getBaseSpawnerRate()).lastCollected(Instant.now()).moneyMultiplier(getBaseMulti()).moneyHolding(0).upgrades(new HashMap<>()).build();
        getAmsService().save(ams);
    }

    public void addUpgrade(Ams ams) {

    }

    public Ams getAms(Location location) {
        ams.putIfAbsent(location, getAmsService().loadByLocation(location));
        return ams.get(location);
    }


}
