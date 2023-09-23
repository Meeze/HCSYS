package mxzx.player.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerData {

    @Id
    private String uuid;
    private String name;
    private long money;
    private int kills;
    private int deaths;
    private boolean bounty;

}
