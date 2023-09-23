package mxzx.chat.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import mxzx.chat.ChatAPI;
import mxzx.chat.model.GlobalPlaceholder;
import mxzx.chat.service.PlaceholderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
@Setter
public class PlaceholderController {

    public static final String PLACEHOLDER_DELIMITER = "%";
    private final Map<String, GlobalPlaceholder> placeholders = new HashMap<>();
    private final List<String> reservedPlaceholderKeys = new ArrayList<>();
    private final PlaceholderService placeholderService;
    private ChatAPI chatAPI;



    public void initPlaceholders() {
        registerPlaceholder(createPlaceholder("rank"));
        registerPlaceholder(createPlaceholder("name"));
        registerPlaceholder(createPlaceholder("chatMessage"));
        getReservedPlaceholderKeys().add("placeholderName");
        getReservedPlaceholderKeys().add("placeholderMatch");
        getReservedPlaceholderKeys().add("value");
    }


    public static String applyDelimiter(String placeholderKey) {
        return PLACEHOLDER_DELIMITER + placeholderKey + PLACEHOLDER_DELIMITER;
    }

    public static String stripPlaceholderDelimiter(String placeholderMatch) {
        return placeholderMatch.replaceAll(PLACEHOLDER_DELIMITER, "");
    }

    public GlobalPlaceholder getPlaceholder(String placeholderKey) {
       return getPlaceholders().get(placeholderKey);
    }

    private void validatePlaceholderKey(String placeholderKey) {
        if (getPlaceholders().containsKey(placeholderKey) || getReservedPlaceholderKeys().contains(placeholderKey)) {
            throw new UnsupportedOperationException("Der Placeholder: " + placeholderKey + " ist bereits registriert!");
        } else {
            getReservedPlaceholderKeys().add(placeholderKey);
        }
    }

    /**
     * saves and registers placeholder
     * @param placeholder created by createPlaceholder
     */
    public void registerPlaceholder(GlobalPlaceholder placeholder) {
        validatePlaceholderKey(placeholder.getPlaceholderKey());
        placeholderService.save(placeholder);
        getPlaceholders().put(placeholder.getPlaceholderKey(), placeholder);
    }

    /**
     * creates placeholder object doesnt save or register
     * @param placeholderKey key
     * @return placeholder object
     */
    public GlobalPlaceholder createPlaceholder(String placeholderKey) {
        GlobalPlaceholder placeholder = new GlobalPlaceholder();
        placeholder.setPlaceholderKey(placeholderKey);
        placeholder.setPlaceholderMatch(applyDelimiter(placeholderKey));
        return placeholder;
    }

    /**
     * loads and inits placeholders
     * @return if database contains placeholders, otherwise load defaults
     */
    public boolean loadGlobalPlaceholders() {
        List<GlobalPlaceholder> placeholders = placeholderService.loadGlobals();
        if (null == placeholders || placeholders.size() < 3) {
            return false;
        }
        placeholders.forEach(this::registerPlaceholder);
        return true;
    }

    public void supply(Player player) {
        boolean canColor = player.hasPermission("chatcolor");
        supplyPlaceholder("rank", player, () ->  "§cOWNAAA");
        supplyPlaceholder("name", player, player.getPlayer()::getName);
        if(canColor) {
            supplyPlaceholder("chatMessage", player, () -> ChatColor.translateAlternateColorCodes('&', chatAPI.getChatController().getChatMessage()));
        } else {
            supplyPlaceholder("chatMessage", player, chatAPI.getChatController()::getChatMessage);
        }
    }

    private String getRank(){
        return "user.getDisplayRank() != null ? user.getDisplayRank(): user.getRank()";
    }

    public void supplyPlaceholder(String placeholderName, Player player, Supplier<String> supplier) {
        getPlaceholder(placeholderName).getValues().put(player, supplier);
    }

}
