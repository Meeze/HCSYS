package mxzx.chat.util;

import org.bukkit.entity.Player;

import java.util.HashMap;


public class Messenger {

    private final HashMap<Player, Player> lastMessenger = new HashMap<>();


    public Messenger() {
    }

    public void sendMsg(Player player, Player target, String message) {
        if(player == target) {
            return;
        }
        lastMessenger.put(player, target);
        message(player, target, message);
    }

    public void reply(Player player, String message) {
        Player target = lastMessenger.get(player);
        if(target != null) {
           message(player, target, message);
        }
    }

    private void message(Player player, Player target, String message) {

    }

}
