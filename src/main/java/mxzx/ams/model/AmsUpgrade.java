package mxzx.ams.model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class AmsUpgrade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private UpgradeType upgradeType;
    private int level;
    private String name;
    private long cost;

}
