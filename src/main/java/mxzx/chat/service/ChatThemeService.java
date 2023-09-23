package mxzx.chat.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.chat.model.ChatTheme;
import mxzx.chat.repository.ChatThemeRepository;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ChatThemeService {

    private final ChatThemeRepository repository;


    public ChatTheme findById(String themeId) {
        return repository.load(themeId);
    }

    public List<ChatTheme> getAll() {
        return repository.loadAll();
    }

    public ChatTheme get(String themeKey) {
        return findById(themeKey);
    }

    public void save(ChatTheme theme) {
         repository.save(theme);
    }

}
