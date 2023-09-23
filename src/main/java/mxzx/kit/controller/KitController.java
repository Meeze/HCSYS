package mxzx.kit.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.abstraccc.Base;
import mxzx.abstraccc.BukkitSerializer;
import mxzx.kit.model.Kit;
import mxzx.kit.service.KitService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class KitController implements Base {

    private final KitService kitService;

    public void createKit(Player player, String name, Inventory inventory) {
        List<Map<String, Object>> inv = Arrays.stream(inventory.getContents()).filter(Objects::nonNull).filter(itemStack -> itemStack.getType() != Material.AIR).map(ItemStack::serialize).collect(Collectors.toList());
        String kitBase64 = BukkitSerializer.playerInventoryToBase64(inventory)[0];
        Kit kit = Kit.builder().inventory(kitBase64).name(name).build();
        getKitService().save(kit);
    }

    public void giveKit(Player player, String name) throws IOException {
        Kit kit = getKitService().load(name);
        ItemStack[] data = BukkitSerializer.itemStackArrayFromBase64(kit.getInventory());
        Arrays.stream(data).filter(Objects::nonNull).forEach(itemStack -> player.getInventory().addItem(itemStack));
    }

}
