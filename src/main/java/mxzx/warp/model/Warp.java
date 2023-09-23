package mxzx.warp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Warp {

    private int x;
    private int y;
    private int z;
    private String world;
    @Id
    private String name;


}
