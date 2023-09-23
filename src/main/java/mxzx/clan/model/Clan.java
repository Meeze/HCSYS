package mxzx.clan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Clan {

    @Id
    private String tag;
    private String owner;
    @OneToMany
    private List<ClanWarp> warps;
    @ElementCollection
    private List<ClanMember> members;


}
