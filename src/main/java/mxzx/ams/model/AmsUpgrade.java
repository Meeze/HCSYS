package mxzx.ams.model;

import javax.persistence.Embeddable;

@Embeddable
public class AmsUpgrade {

    private String name;
    private int level;
    private long cost;

}
