package mxzx.kit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mxzx.database.convert.InventoryConverter;
import org.bukkit.inventory.ItemStack;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Kit {

    @Id
    private String name;
    @Convert(converter = InventoryConverter.class)
    private ItemStack[] content;

}
