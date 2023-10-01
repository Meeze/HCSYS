package mxzx.ams.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mxzx.database.convert.LocationConverter;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
// you might wonder where the block or location is? jokes on you pleb dev, its not here ;) its not bound to a loc rather bound to a block containing the id of ams which works by placing it.
public class Ams {

    @Id
    @Convert(converter = LocationConverter.class)
    private Location location;
    @NaturalId
    private String owner;
    @OneToMany
    private Map<UpgradeType, AmsUpgrade> upgrades;
    private long moneyHolding;
    private long moneyLimit;
    private long spawner;
    private Instant lastCollected;
    private long baseSpawnerRate = 5;
    private long moneyMultiplier = 1;

}
