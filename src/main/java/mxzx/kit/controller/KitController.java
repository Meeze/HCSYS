package mxzx.kit.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx._core.Base;
import mxzx.kit.model.Kit;
import mxzx.kit.service.KitService;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class KitController implements Base {

    private final KitService kitService;

    public void createKit(Player player, String name, Inventory inventory) {
        //List<Map<String, Object>> inv = Arrays.stream(inventory.getContents()).filter(Objects::nonNull).filter(itemStack -> itemStack.getType() != Material.AIR).map(ItemStack::serialize).collect(Collectors.toList());
        //String kitBase64 = BukkitSerializer.playerInventoryToBase64(inventory)[0];
        Kit kit = Kit.builder().content(inventory.getContents()).name(name).build();
        getKitService().save(kit);
    }

    public void giveKit(Player player, String name) throws IOException {
        Kit kit = getKitService().load(name);
        Arrays.stream(kit.getContent()).filter(Objects::nonNull).forEach(itemStack -> player.getInventory().addItem(itemStack));
    }

}
