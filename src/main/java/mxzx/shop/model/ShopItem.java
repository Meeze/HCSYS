package mxzx.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import mxzx.database.convert.ItemStackConverter;
import org.bukkit.inventory.ItemStack;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopItem {

    private String id;
    private String displayName;
    @Convert(converter = ItemStackConverter.class)
    private ItemStack b64itemStack;
    private long price;
}
