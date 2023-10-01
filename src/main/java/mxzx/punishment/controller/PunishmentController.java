package mxzx.punishment.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import mxzx._core.Base;
import mxzx.punishment.model.Punishment;
import mxzx.punishment.model.PunishmentResult;
import mxzx.punishment.model.PunishmentType;
import mxzx.punishment.service.PunishmentService;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class PunishmentController implements Base {

    private HashMap<String, List<Punishment>> punishments = new HashMap<>();
    private final PunishmentService punishmentService;

    private void addOrInit(String affectedPlayerId, Punishment punishment) {
        if (getPunishments().containsKey(affectedPlayerId)) {
            List<Punishment> existingPunishments = getPunishments().get(affectedPlayerId);
            existingPunishments.removeIf(existingPunish -> existingPunish.getPunishmentType() == punishment.getPunishmentType());
            existingPunishments.add(punishment);
            getPunishments().put(affectedPlayerId, existingPunishments);
        } else {
            getPunishments().put(affectedPlayerId, new ArrayList<Punishment>() {{
                add(punishment);
            }});
        }
    }

    private void removePunishment() {

    }

    private PunishmentResult addToPunishments(String affectedPlayer, Punishment punishment) {
        Optional<Punishment> potentialExistingPunishment = getPunishment(affectedPlayer, punishment.getPunishmentType());
        if (potentialExistingPunishment.isPresent() && potentialExistingPunishment.get().getExpiresAt().isBefore(punishment.getExpiresAt())) {
            addOrInit(affectedPlayer, punishment);
            return PunishmentResult.OVERRIDE_PUNISHMENT;
        }
        if (potentialExistingPunishment.isPresent()) {
            return PunishmentResult.ALREADY_PUNISHED;
        }
        addOrInit(affectedPlayer, punishment);
        return PunishmentResult.SUCCESS;
    }


    public void init() {
        setPunishments(new HashMap<>());
        List<Punishment> loadedPunishments = getPunishmentService().loadAll();
        loadedPunishments.forEach(punishment -> {
            Bukkit.broadcastMessage(punishment.getReason());
            addOrInit(punishment.getPunishmentReceiver().toString(), punishment);
        });
    }

    private Punishment buildPunishment(UUID issuer, UUID receiver, String reason, Instant expiresAt, PunishmentType punishmentType) {
        Instant issuedAt = Instant.now();
        Punishment punishment = new Punishment();
        punishment.setPunishmentIssuer(issuer);
        punishment.setPunishmentReceiver(receiver);
        punishment.setReason(reason);
        punishment.setIssuedAt(issuedAt);
        punishment.setExpiresAt(expiresAt);
        punishment.setPunishmentType(punishmentType);
        return punishment;
    }

    private UUID getUUIDFromPlayer(Player player) {
        return player.getUniqueId();
    }


    public Punishment build(Player issuer, String receiverId, String reason, Instant expiresAt, PunishmentType type) {
        switch (type) {
            case BAN:
                return buildPunishment(getUUIDFromPlayer(issuer), UUID.fromString(receiverId), reason, expiresAt, PunishmentType.BAN);
            case PVPBAN:
                return buildPunishment(getUUIDFromPlayer(issuer), UUID.fromString(receiverId), reason, expiresAt, PunishmentType.PVPBAN);
            case FREEZE:
                return buildPunishment(getUUIDFromPlayer(issuer), UUID.fromString(receiverId), reason, expiresAt, PunishmentType.FREEZE);
            case KICK:
                return buildPunishment(getUUIDFromPlayer(issuer), UUID.fromString(receiverId), reason, null, PunishmentType.KICK);
            case MUTE:
                return buildPunishment(getUUIDFromPlayer(issuer), UUID.fromString(receiverId), reason, expiresAt, PunishmentType.MUTE);
            default:
                return new Punishment();
        }
    }

    private boolean cantPunish(UUID receiverId) {
        OfflinePlayer offTarget = Bukkit.getOfflinePlayer(receiverId);
        PermissionManager pex = PermissionsEx.getPermissionManager();
        PermissionUser user = PermissionsEx.getUser(offTarget.getName());
        return user.has("system.ban.bypass");
    }


    private void save(Punishment punishment) {
        getPunishmentService().save(punishment);
    }


    /**
     * @param punishment from builder
     * @return result of action, handle in command accordingly
     */
    public PunishmentResult executePunishment(Punishment punishment) {
        if (cantPunish(punishment.getPunishmentReceiver())) {
            return PunishmentResult.PERMISSION_DENIED;
        }
        PunishmentType type = punishment.getPunishmentType();
        PunishmentResult result = PunishmentResult.UNKNOWN;
        switch (type) {
            case BAN:
                result = doBan(punishment);
                if (result.equals(PunishmentResult.SUCCESS) || result.equals(PunishmentResult.OVERRIDE_PUNISHMENT)) {
                    save(punishment);
                    if(Bukkit.getOfflinePlayer(punishment.getPunishmentReceiver()).isOnline()) {
                        doKick(punishment);
                    }
                }
                return result;
            case KICK:
                return doKick(punishment);
            case MUTE:
                result = doMute(punishment);
                if (result.equals(PunishmentResult.SUCCESS) || result.equals(PunishmentResult.OVERRIDE_PUNISHMENT)) {
                    save(punishment);
                }
                return result;
            case FREEZE:
                result = doBan(punishment);
                if (result.equals(PunishmentResult.SUCCESS) || result.equals(PunishmentResult.OVERRIDE_PUNISHMENT)) {
                    save(punishment);
                }
            case PVPBAN:
                result = doBan(punishment);
                if (result.equals(PunishmentResult.SUCCESS) || result.equals(PunishmentResult.OVERRIDE_PUNISHMENT)) {
                    save(punishment);
                    if(Bukkit.getOfflinePlayer(punishment.getPunishmentReceiver()).isOnline()) {
                        doKick(punishment);
                    }
                }
                return result;
            default:
                return result;
        }
    }

    private PunishmentResult doBan(Punishment punishment) {
        return addToPunishments(punishment.getPunishmentReceiver().toString(), punishment);
    }

    private PunishmentResult doKick(Punishment punishment) {
        return PunishmentResult.SUCCESS;
    }

    private PunishmentResult doMute(Punishment punishment) {
        return addToPunishments(punishment.getPunishmentReceiver().toString(), punishment);
    }

    public Optional<Punishment> getPunishment(String playerId, PunishmentType punishmentType) {
        List<Punishment> punishments = getPunishments().getOrDefault(playerId, new ArrayList<>());
        return punishments.stream().filter(p -> p.getPunishmentType().equals(punishmentType)).findFirst();
    }

    public List<Punishment> getPunishment(String playerId) {
        return getPunishments().getOrDefault(playerId, new ArrayList<>());
    }

    public boolean isPunishmentExpired(Punishment punishment) {
        return punishment.getExpiresAt().isBefore(Instant.now());
    }


    public boolean unban(String uuid) {
        Optional<Punishment> ban = getPunishment(uuid, PunishmentType.BAN);
        if (ban.isPresent()) {
            Punishment punish = ban.get();
            getPunishments().get(uuid).remove(punish);
            getPunishmentService().delete(punish);
            return true;
        } else {
            return false;
        }
    }

    public boolean unmute(String uuid) {
        Optional<Punishment> mute = getPunishment(uuid, PunishmentType.MUTE);
        if (mute.isPresent()) {
            Punishment punish = mute.get();
            getPunishments().get(uuid).remove(punish);
            getPunishmentService().delete(punish);
            return true;
        } else {
            return false;
        }
    }

    public boolean unfreeze(String uuid) {
        Optional<Punishment> punishment = getPunishment(uuid, PunishmentType.FREEZE);
        if (punishment.isPresent()) {
            Punishment punish = punishment.get();
            getPunishments().get(uuid).remove(punish);
            getPunishmentService().delete(punish);
            return true;
        } else {
            return false;
        }
    }

    public String buildReason(String[] reasonArgs, Boolean isSilent) {
        StringBuilder stringBuilder = new StringBuilder();
        //fix for not including reason sometimes idk why this even happens
        if (reasonArgs.length == 1) {
            return stringBuilder.append(reasonArgs[0]).toString();
        }
        for (int i = 0; i < reasonArgs.length; i++) {
            if (isSilent != null && i == reasonArgs.length - 1) {
                // dont include silent arg (-s/.v)
                continue;
            } else {
                stringBuilder.append(reasonArgs[i]).append(" ");
            }
        }
        return stringBuilder.toString();
    }

}
