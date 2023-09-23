package mxzx.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "theme")
public class ChatTheme {

    @Id
    private String themeKey;
    private String format;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "theme_placeholders")
    private Set<ThemePlaceholder> themePlaceholders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "theme_global_placeholders")
    private Set<GlobalPlaceholderAction> globalPlaceholderActions = new HashSet<>();
    @Transient
    private List<String> activeUserUUIDS = new ArrayList<>();

}
