package mxzx.punishment.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@ToString
public class Punishment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID punishmentId;
    private String reason;
    private Instant issuedAt;
    private Instant expiresAt;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID punishmentIssuer;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID punishmentReceiver;
    @Enumerated(value = EnumType.STRING)
    private PunishmentType punishmentType;

}

