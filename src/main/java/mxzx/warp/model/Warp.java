package mxzx.warp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mxzx.database.convert.LocationConverter;
import org.bukkit.Location;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Warp {

    @Id
    private String name;
    @Convert(converter = LocationConverter.class)
    private Location location;

}
