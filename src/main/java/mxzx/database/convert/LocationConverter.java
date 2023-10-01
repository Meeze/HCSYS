package mxzx.database.convert;

import mxzx._core.BukkitSerializer;
import org.bukkit.Location;

import javax.persistence.AttributeConverter;

public class LocationConverter implements
        AttributeConverter<Location, String> {

    @Override
    public String convertToDatabaseColumn(Location location) {
        if (location == null) {
            return null;
        }
        return BukkitSerializer.locToBase64(location);
    }

    @Override
    public Location convertToEntityAttribute(String location) {
        try {
            if (location == null ||location.equalsIgnoreCase("")) {
                return null;
            }
            return BukkitSerializer.locFromb64(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}