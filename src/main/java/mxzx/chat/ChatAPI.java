package mxzx.chat;


import mxzx.chat.controller.ChatController;
import mxzx.chat.controller.ChatThemeController;
import mxzx.chat.controller.PlaceholderController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mxzx.chat.model.ChatTheme;
import mxzx.chat.model.GlobalPlaceholder;
import mxzx.chat.model.GlobalPlaceholderAction;
import mxzx.chat.model.ThemePlaceholder;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Level;

@Getter
@RequiredArgsConstructor
public class ChatAPI {

    private final ChatThemeController chatThemeController;
    private final PlaceholderController placeholderController;
    private final ChatController chatController;


    public void injectApi() {
        chatController.setChatAPI(this);
        placeholderController.setChatAPI(this);
        chatController.setChatAPI(this);
    }

    public void bootstrap() {
        if (!getPlaceholderController().loadGlobalPlaceholders()) {
            Bukkit.getLogger().log(Level.INFO, "No placeholder found, creating defaults");
            getPlaceholderController().initPlaceholders();
        }
        if (!getChatThemeController().loadThemes()) {
            Bukkit.getLogger().log(Level.INFO, "No chat themes found, creating defaults");
            getChatThemeController().createAndSaveDefaults();
        }
    }


    public void login(Player player) {
        supply(player);
    }

    // CHAT
    public void chat(Player player, String message) {
        getChatController().chat(player, message);
    }

    //THEME
    public Collection<ChatTheme> getThemes() {
        return getChatThemeController().getThemes().values();
    }

    public boolean setTheme(Player player, String themeKey) {
        return getChatThemeController().setTheme(player, themeKey);
    }

    public ChatTheme createTheme(String themeKey, Set<GlobalPlaceholderAction> globalPlaceholderActions, Set<ThemePlaceholder> themePlaceholders, String format) {
        return getChatThemeController().createTheme(themeKey, globalPlaceholderActions, themePlaceholders, format);
    }

    public ChatTheme getTheme(String themeKey) {
        return getChatThemeController().getTheme(themeKey);
    }

    // PLACEHOLDER
    public void registerPlaceholder(GlobalPlaceholder placeholder) {
        getPlaceholderController().registerPlaceholder(placeholder);
    }

    public GlobalPlaceholder getPlaceholder(String placeholder) {
        return getPlaceholderController().getPlaceholder(placeholder);
    }

    public void supply(Player player) {
       getPlaceholderController().supply(player);
    }

    public void supplyPlaceholders(String placeholderName, Player player, Supplier<String> supplier) {
        getPlaceholderController().supplyPlaceholder(placeholderName, player, supplier);
    }

}
