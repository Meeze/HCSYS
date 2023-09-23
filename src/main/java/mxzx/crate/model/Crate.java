package mxzx.crate.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Crate {

    @Id
    private String name;
    private CrateRarity rarity;
    private long cost;
    @ElementCollection
    private List<CrateItem> items;

}
