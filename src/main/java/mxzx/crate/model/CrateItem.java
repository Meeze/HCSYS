package mxzx.crate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrateItem {

    @Id
    @GeneratedValue
    private String id;
    private String displayName;
    @Lob
    private String b64itemStack;
    private int tickets;
    // higher = shown first in crate preview and such
    private int viewWeight;

}
