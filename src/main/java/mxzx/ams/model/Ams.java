package mxzx.ams.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mxzx.database.DatabaseLocation;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ams {

    @GeneratedValue
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @NaturalId
    private String owner;
    @ElementCollection
    private List<AmsUpgrade> upgrades;
    private long money;
    private DatabaseLocation location;

}
