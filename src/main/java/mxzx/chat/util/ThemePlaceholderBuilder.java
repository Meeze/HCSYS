package mxzx.chat.util;

import lombok.Getter;
import mxzx.chat.model.ThemePlaceholder;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ThemePlaceholderBuilder {

    private final Set<ThemePlaceholder> placeholderSet = new HashSet<>();

    public static ThemePlaceholderBuilder create() {
        return new ThemePlaceholderBuilder();
    }

    public ThemePlaceholderBuilder add(ThemePlaceholder themePlaceholder) {
        placeholderSet.add(themePlaceholder);
        return this;
    }

    public Set<ThemePlaceholder> build() {
        return getPlaceholderSet();
    }

}
