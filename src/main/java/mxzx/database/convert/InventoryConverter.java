package mxzx.database.convert;

import mxzx._core.BukkitSerializer;
import org.bukkit.inventory.ItemStack;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class InventoryConverter implements
        AttributeConverter<ItemStack[], String> {

    @Override
    public String convertToDatabaseColumn(ItemStack[] inventory) {
        if (inventory == null) {
            return null;
        }
        return BukkitSerializer.itemStackArrayToBase64(inventory);
    }

    @Override
    public ItemStack[] convertToEntityAttribute(String item) {
        return BukkitSerializer.itemStackArrayFromBase64(item);
    }
}
