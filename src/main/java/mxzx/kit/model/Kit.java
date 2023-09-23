package mxzx.kit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.Inventory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Kit {

    @Id
    private String name;
    @Lob
    private String inventory;

}
