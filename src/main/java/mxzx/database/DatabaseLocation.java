package mxzx.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseLocation {

    private String name;
    private String world;
    private long x;
    private long y;
    private long z;

}
