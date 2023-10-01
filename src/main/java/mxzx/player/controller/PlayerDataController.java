package mxzx.player.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx._core.BaseController;
import mxzx.player.model.PlayerData;
import mxzx.player.service.PlayerDataService;

import java.util.HashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class PlayerDataController implements BaseController {

    private Map<String, PlayerData> playerDataCache = new HashMap<>();

    private final PlayerDataService playerDataService;

    public void setUp(String uuid) {
        PlayerData loaded = playerDataService.load(uuid);
        if (null == loaded || null == loaded.getUuid()) {
            PlayerData playerData = PlayerData.builder().uuid(uuid).kills(0).deaths(0).money(1000).build();
            playerDataService.save(playerData);
            playerDataCache.put(uuid, playerData);
        } else {
            playerDataCache.put(uuid, loaded);
        }
    }

    public PlayerData getData(String uuid) {
        playerDataCache.putIfAbsent(uuid, playerDataService.load(uuid));
        return playerDataCache.get(uuid);
    }

    public long getMoney(String uuid) {
        return getData(uuid).getMoney();
    }

    public void removeMoney(String uuid, long amount) {
        getData(uuid).setMoney(getData(uuid).getMoney() - amount);
    }

    public void addMoney(String uuid, long amount) {
        getData(uuid).setMoney(getData(uuid).getMoney() + amount);
    }

    public long getKills(String uuid) {
        return getData(uuid).getKills();
    }

    public void removeKills(String uuid, int amount) {
        getData(uuid).setKills(getData(uuid).getKills() - amount);
    }

    public void addKills(String uuid, int amount) {
        getData(uuid).setKills(getData(uuid).getKills() + amount);
    }

    public void addMoney(String uuid, int amount) {
        getData(uuid).setKills(getData(uuid).getKills() + amount);
    }

    public long getDeaths(String uuid) {
        return getData(uuid).getDeaths();
    }

    public void removeDeaths(String uuid, int amount) {
        getData(uuid).setDeaths(getData(uuid).getDeaths() - amount);
    }

    public void addDeaths(String uuid, int amount) {
        getData(uuid).setDeaths(getData(uuid).getDeaths() + amount);
    }

    public boolean isHunted(Player player) {
        return getData(playerId(player)).isBounty();
    }
    public void setHunted(Player player, boolean hunted) {
        getData(playerId(player)).setBounty(hunted);
    }

}