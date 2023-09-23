package mxzx.command;


import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Syntax;
import mxzx.abstraccc.BasedCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import mxzx.punishment.controller.PunishmentController;
import mxzx.punishment.model.Punishment;
import mxzx.punishment.model.PunishmentResult;
import mxzx.punishment.model.PunishmentType;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@AllArgsConstructor
@CommandAlias("punishment|punish")
public class PunishmentCommand extends BasedCommand {

    private final PunishmentController punishmentController;

    @CommandAlias("debug")
    public void onDebug(Player player, String target) {
        OfflinePlayer receiver = Bukkit.getOfflinePlayer(target);
        List<Punishment> punishments = getPunishmentController().getPunishment(receiver.getUniqueId().toString());
        punishments.forEach(punishment -> player.sendMessage(punishment.toString()));

    }

    @Syntax("<Player> <perma|w|d|h|m> <Grund...>\"")
    @CommandAlias("ban")
    @CommandCompletion("@players @punishment-time grund")
    public void onBan(Player player, String target, String expires, String[] args) {
        OfflinePlayer receiver = Bukkit.getOfflinePlayer(target);
        String reason = buildMessageFromArguments(args, 0);
        Instant expiresAt = parsePunishTime(expires);
        if (null == expiresAt) {
            player.sendMessage("ungültige zeit");
            return;
        }
        Punishment ban = getPunishmentController().build(player, receiver.getUniqueId().toString(), reason, expiresAt, PunishmentType.BAN);
        PunishmentResult result = getPunishmentController().executePunishment(ban);
        switch (result) {
            case OVERRIDE_PUNISHMENT:
            case SUCCESS:
                player.sendMessage("Du hast xyz gebannt");
                Bukkit.broadcastMessage(receiver.getName() + " wurde verlängtgebannt! Grund: " + ban.getReason());
                return;
            case PERMISSION_DENIED:
                player.sendMessage("Du kannst diesen Spieler nicht bannen");
                return;
            case ALREADY_PUNISHED:
                player.sendMessage("Der Spieler ist bereits länger gebannt als " + expiresAt);
                return;
            case UNKNOWN:
                Bukkit.broadcastMessage("this should never happen");
                return;
        }

    }

    @Syntax("<Player> <perma|w|d|h|m> <Grund...>\"")
    @CommandAlias("pvpban")
    @CommandCompletion("@players @punishment-time grund")
    public void onPvpBan(Player player, String target, String expires, String[] args) {
        OfflinePlayer receiver = Bukkit.getOfflinePlayer(target);
        String reason = buildMessageFromArguments(args, 0);
        Instant expiresAt = parsePunishTime(expires);
        if (null == expiresAt) {
            player.sendMessage("ungültige zeit");
            return;
        }
        Punishment ban = getPunishmentController().build(player, receiver.getUniqueId().toString(), reason, expiresAt, PunishmentType.PVPBAN);
        PunishmentResult result = getPunishmentController().executePunishment(ban);
        switch (result) {
            case OVERRIDE_PUNISHMENT:
            case SUCCESS:
                player.sendMessage("Du hast xyz pvp gebannt");
                Bukkit.broadcastMessage(receiver.getName() + " wurde verlängtgebannt! Grund: " + ban.getReason());
                return;
            case PERMISSION_DENIED:
                player.sendMessage("Du kannst diesen Spieler nicht bannen");
                return;
            case ALREADY_PUNISHED:
                player.sendMessage("Der Spieler ist bereits länger gebannt als " + expiresAt);
                return;
            case UNKNOWN:
                Bukkit.broadcastMessage("this should never happen");
                return;
        }
    }

    @CommandAlias("mute")
    @Syntax("<Player> <perma|w|d|h|m> <Grund...>\"")
    @CommandCompletion("@players @punishment-time grund")
    public void onMute(Player player, String target, String expires, String[] args) {
        Player receiver = Bukkit.getPlayer(target);
        if (null == receiver) {
            player.sendMessage("ungültiges ziel");
            return;
        }
        String reason = buildMessageFromArguments(args, 0);
        Instant expiresAt = parsePunishTime(expires);
        if (null == expiresAt) {
            player.sendMessage("ungültige zeit");
            return;
        }
        Punishment mute = getPunishmentController().build(player, receiver.getUniqueId().toString(), reason, expiresAt, PunishmentType.MUTE);
        PunishmentResult result = getPunishmentController().executePunishment(mute);
        switch (result) {
            case OVERRIDE_PUNISHMENT:
            case SUCCESS:
                player.sendMessage("Du hast xyz muted");
                Bukkit.broadcastMessage(receiver.getName() + " wurde gemuted! Grund: " + mute.getReason());
                return;
            case PERMISSION_DENIED:
                player.sendMessage("Du kannst diesen Spieler nicht muten" + expiresAt);
                return;
            case ALREADY_PUNISHED:
                player.sendMessage("Der Spieler ist bereits länger gemuted als " + expiresAt);
                return;
            case UNKNOWN:
                Bukkit.broadcastMessage("this should never happen");
                return;
        }
    }

    @CommandAlias("kick")
    @Syntax("<Player> <Grund...>\"")
    @CommandCompletion("@players grund")
    public void onKick(Player player, String target, String[] args) {
        Player receiver = Bukkit.getPlayer(target);
        String reason = buildMessageFromArguments(args, 0);
        Punishment punishment = getPunishmentController().build(player, receiver.getUniqueId().toString(), reason, null, PunishmentType.KICK);
        PunishmentResult result = getPunishmentController().executePunishment(punishment);
        switch (result) {
            case SUCCESS:
                player.sendMessage("Du hast xyz gekickt");
                Bukkit.broadcastMessage(receiver.getName() + " wurde gekickt! Grund: " + punishment.getReason());
                return;
            case PERMISSION_DENIED:
                player.sendMessage("Du kannst diesen Spieler nicht kicken");
                return;
            case ALREADY_PUNISHED:
            case OVERRIDE_PUNISHMENT:
            case UNKNOWN:
                Bukkit.broadcastMessage("this should never happen");
                return;
        }
    }

    @CommandAlias("freeze")
    @Syntax("<Player> <Grund...>\"")
    @CommandCompletion("@players grund")
    public void onFreeze(Player player, String target, String expires, String[] args) {
        Player receiver = Bukkit.getPlayer(target);
        String reason = buildMessageFromArguments(args, 0);
        Instant expiresAt = parsePunishTime(expires);
        if (null == expiresAt) {
            player.sendMessage("ungültige zeit");
            return;
        }
        Punishment punishment = getPunishmentController().build(player, receiver.getUniqueId().toString(), reason, expiresAt, PunishmentType.FREEZE);
        PunishmentResult result = getPunishmentController().executePunishment(punishment);
        switch (result) {
            case SUCCESS:
                player.sendMessage("Du hast xyz gefruru");
                Bukkit.broadcastMessage(receiver.getName() + " wurde gertzrt! Grund: " + punishment.getReason());
                return;
            case PERMISSION_DENIED:
                player.sendMessage("Du kannst diesen Spieler nicht freezen");
                return;
            case ALREADY_PUNISHED:
            case OVERRIDE_PUNISHMENT:
            case UNKNOWN:
                Bukkit.broadcastMessage("this should never happen");
                return;
        }
    }


    protected Instant parsePunishTime(String timeToAdd) {
        if (timeToAdd.equalsIgnoreCase("perma")) {
            return Instant.MAX;
        } else {
            String timeWithoutUnit = timeToAdd.substring(0, timeToAdd.length() - 1);
            if (!timeWithoutUnit.matches("\\d+")) {
                return null;
            }
            // last char is unit
            switch (timeToAdd.charAt(timeToAdd.length() - 1)) {
                case 'w':
                    return Instant.now().plus(Integer.parseInt(timeWithoutUnit) * 7L, ChronoUnit.DAYS);
                case 'd':
                    return Instant.now().plus(Integer.parseInt(timeWithoutUnit), ChronoUnit.DAYS);
                case 'h':
                    return Instant.now().plus(Integer.parseInt(timeWithoutUnit), ChronoUnit.HOURS);
                case 'm':
                    return Instant.now().plus(Integer.parseInt(timeWithoutUnit), ChronoUnit.MINUTES);
                default:
                    return null;
            }
        }
    }

}
