package mxzx.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@Entity
@Table(name = "global_placeholder")
public class GlobalPlaceholder {

    @Id
    @Column(name = "placeholder_key")
    private String placeholderKey;

    private String placeholderMatch;

    @Transient
    private Map<Player, Supplier<String>> values = new HashMap<>();

    public String getValue(Player player) {
        return getValues().get(player).get();
    }

}
