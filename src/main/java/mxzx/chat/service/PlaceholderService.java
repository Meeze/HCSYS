package mxzx.chat.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mxzx.chat.model.GlobalPlaceholder;
import mxzx.chat.model.ThemePlaceholder;
import mxzx.chat.repository.GlobalPlaceholderRepository;
import mxzx.chat.repository.ThemePlaceholderRepository;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class PlaceholderService {

    private final GlobalPlaceholderRepository globalPlaceholderRepository;
    private final ThemePlaceholderRepository themeRepository;

    public List<GlobalPlaceholder> loadGlobals(){
        return globalPlaceholderRepository.loadAll();
    }

    public void save(GlobalPlaceholder globalPlaceholder) {
         globalPlaceholderRepository.save(globalPlaceholder);
    }

    public void save(ThemePlaceholder themePlaceholder) {
         themeRepository.save(themePlaceholder);
    }

}
