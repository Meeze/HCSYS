package mxzx.warp.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import mxzx.warp.model.Warp;
import mxzx.warp.service.WarpService;

import java.util.Map;

@RequiredArgsConstructor
@Data
public class WarpController {

    private Map<String, Warp> warps;
    private final WarpService warpService;

    public void setUp() {
        setWarps(getWarpService().loadWarps());
    }

    public boolean hasWarp(String name) {
        return getWarps().containsKey(name);
    }

    public Warp getWarp(String name) {
        return getWarps().get(name);
    }

    public void createWarp(String name, Player player) {
        Location l = player.getLocation();
        Warp warp = Warp.builder().name(name).x(l.getBlockX()).y(l.getBlockY()).z(l.getBlockZ()).build();
        warps.put(name, warp);
        getWarpService().saveWarp(warp);
    }

}
