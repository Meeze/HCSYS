package mxzx.chat.controller;


import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import mxzx.chat.ChatAPI;
import mxzx.chat.model.ChatTheme;
import mxzx.chat.model.PlaceholderAction;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ChatController {

    private String chatMessage;
    private ChatAPI chatAPI;

    public void chat(Player player, String message) {
        setChatMessage(message);
        chatAPI.getThemes().forEach(chatTheme -> {
            if (chatTheme.getActiveUserUUIDS().size() < 1) {
                return;
            }
            //MSG msg = buildThemeMessage(chatTheme, player);
          //  msg.sendAll(chatTheme.getActiveUsers(), new ArrayList<>());
        });

    }

    /**
     * creates a map out of all themeplaceholders and globalplaceholderactions with placeholder as key
     * @param theme to get actions from
     * @return map of all actions with delimited placeholder as key
     */
    public Map<String, PlaceholderAction> getActionsMap(ChatTheme theme) {
        Map<String, PlaceholderAction> actions = new HashMap<>();
        theme.getGlobalPlaceholderActions().forEach(globalPlaceholderAction -> mergeActionsIntoMap(actions, globalPlaceholderAction));
        theme.getThemePlaceholders().forEach(themePlaceholder -> mergeActionsIntoMap(actions, themePlaceholder));
        return actions;
    }

    private void mergeActionsIntoMap(Map<String, PlaceholderAction> actions, PlaceholderAction placeholderAction) {
        actions.put(PlaceholderController.applyDelimiter(placeholderAction.getPlaceholderKey()), placeholderAction);
    }

    /**
     * creates chat message in a theme for a given player
     * @param theme to use
     * @param player who chatted
     * @return themed chatmessage for player

    private MSG buildThemeMessage(ChatTheme theme, Player player) {
        MSG msg = new MSG(theme);
        Map<String, PlaceholderAction> actions = getActionsMap(theme);
        msg.createThemeParts(actions, player, chatAPI.getPlaceholderController());
        return msg;
      */
}
