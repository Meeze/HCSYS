package mxzx.punishment.model;

import lombok.Getter;

@Getter
public enum PunishmentType {

    KICK("KICK"),
    MUTE("MUTE"),
    FREEZE("FREEZE"),
    PVPBAN("PVPBAN"),
    BAN("BAN");

    private final String name;

    PunishmentType(String name) {
        this.name = name;
    }
}
