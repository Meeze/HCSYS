package mxzx.chat.model;

public interface PlaceholderAction {
    String getPlaceholderKey();
    ChatHoverAction getHoverAction();
    ChatClickAction getClickAction();
}
