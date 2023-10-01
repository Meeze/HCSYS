package mxzx.clan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mxzx.database.convert.LocationConverter;
import org.bukkit.Location;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ClanWarp {

    @Convert(converter = LocationConverter.class)
    private Location location;
    private String name;


}