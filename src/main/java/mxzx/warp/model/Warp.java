package mxzx.warp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mxzx.database.DatabaseLocation;

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
    private DatabaseLocation databaseLocation;

}
