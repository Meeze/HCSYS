package mxzx.command.basic;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import mxzx._core.BasedCommand;

@CommandAlias("stack")
@CommandPermission("system.command.stack")
public class StackCommand extends BasedCommand {

    @Syntax("/stack")
    @Default
    public void onCommand(Player player, String[] args) {
        ItemStack[] contents = player.getInventory().getContents();
        for (int i = 0; i < contents.length; ++i) {
            ItemStack current = contents[i];
            if (current == null || current.getType() == Material.AIR || current.getAmount() <= 0 || current.getAmount() >= 64)
                continue;
            int needed = 64 - current.getAmount();
            for (int i2 = i + 1; i2 < contents.length; ++i2) {
                ItemStack nextCurrent = contents[i2];
                if (nextCurrent == null || nextCurrent.getType() == Material.AIR || nextCurrent.getAmount() <= 0 || current.getType() != nextCurrent.getType() || current.getDurability() != nextCurrent.getDurability() || (current.getItemMeta() != null || nextCurrent.getItemMeta() != null) && (current.getItemMeta() == null || !current.getItemMeta().equals((Object) nextCurrent.getItemMeta())))
                    continue;
                if (nextCurrent.getAmount() > needed) {
                    current.setAmount(64);
                    nextCurrent.setAmount(nextCurrent.getAmount() - needed);
                    continue;
                }
                contents[i2] = null;
                current.setAmount(current.getAmount() + nextCurrent.getAmount());
                needed = 64 - current.getAmount();
            }
        }
    }

}

