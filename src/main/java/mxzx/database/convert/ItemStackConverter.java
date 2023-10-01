package mxzx.database.convert;

import mxzx._core.BukkitSerializer;
import org.bukkit.inventory.ItemStack;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ItemStackConverter implements
        AttributeConverter<ItemStack, String> {

    @Override
    public String convertToDatabaseColumn(ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        return BukkitSerializer.itemStackToBase64(itemStack);
    }

    @Override
    public ItemStack convertToEntityAttribute(String item) {
        return BukkitSerializer.itemStackFromBase64(item);
    }
}
