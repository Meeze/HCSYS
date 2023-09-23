package mxzx.ams.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.abstraccc.Base;
import mxzx.ams.model.Ams;
import mxzx.database.Repository;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public class AmsService implements Base {

    private final Repository<Ams> amsRepository;

    public void saveAms(Ams ams) {
       getAmsRepository().save(ams);
    }

}
