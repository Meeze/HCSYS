package mxzx.crate.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.abstraccc.Base;
import mxzx.abstraccc.BukkitSerializer;
import mxzx.abstraccc.ItemBuilder;
import mxzx.crate.model.Crate;
import mxzx.crate.model.CrateItem;
import mxzx.crate.model.CrateRarity;
import mxzx.crate.service.CrateService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class CrateController implements Base {


    private final CrateService crateService;
    private final Map<String, Crate> crates = new HashMap<>();

    public void setUp() {
        getCrateService().loadAll().forEach(crate -> crates.put(crate.getName(),crate));
    }

    private Crate getCrate(String crateName) {
        getCrates().putIfAbsent(crateName, getCrateService().loadCrate(crateName));
        return getCrates().get(crateName);
    }

    public void createCrate(String name, CrateRarity rarity, long cost) {
        Crate crate = Crate.builder().name(name).rarity(rarity).items(new ArrayList<>()).cost(cost).build();
        crates.put(crate.getName(),crate);
        getCrateService().saveCrate(crate);
    }

    public void addItemToCrate(String crateName, String itemName, ItemStack item, int tickets, int viewWeight) {
        CrateItem crateItem = createCrateItem(itemName, item, tickets, viewWeight);
        getCrateService().saveItem(crateItem);
        Crate crate = getCrate(crateName);
        crate.getItems().add(crateItem);
        crates.put(crate.getName(), crate);
        getCrateService().updateCrate(crate);
    }

    public CrateItem createCrateItem(String name, ItemStack itemStack, int tickets, int viewWeight) {
        String serial = BukkitSerializer.itemStackArrayToBase64(itemArr(itemStack));
        return CrateItem.builder().displayName(name).b64itemStack(serial).tickets(tickets).viewWeight(viewWeight).build();
    }

    private void addItemToCrate(String crateName, CrateItem crateItem) {
        getCrate(crateName).getItems().add(crateItem);
    }

}
