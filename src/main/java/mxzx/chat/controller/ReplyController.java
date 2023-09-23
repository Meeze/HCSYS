package mxzx.chat.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import mxzx.abstraccc.BaseController;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ReplyController implements BaseController {

    private final Map<Player, Player> lastMessengerMap = new HashMap<>();

    public void addLastMessenger(Player messageReceiver, Player messenger){
        this.lastMessengerMap.remove(messageReceiver);
        this.lastMessengerMap.put(messageReceiver, messenger);
    }

}
