package mxzx.warp.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.database.Repository;
import mxzx.warp.model.Warp;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class WarpService {

    private final Repository<Warp> warpRepository;

    public Map<String, Warp> loadWarps() {
        Map<String, Warp> warps = new HashMap<>();
        getWarpRepository().loadAll().forEach(warp ->  warps.put(warp.getName(), warp));
        return warps;
    }

    public void saveWarp(Warp warp) {
        warpRepository.save(warp);
    }

    public void deleteWarp(Warp warp) {
        warpRepository.delete(warp);
    }

}
