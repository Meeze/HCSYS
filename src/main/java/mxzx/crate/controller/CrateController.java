package mxzx.crate.controller;

import lombok.RequiredArgsConstructor;
import mxzx.crate.model.Crate;
import mxzx.crate.model.CrateItem;
import mxzx.crate.model.CrateRarity;
import mxzx.crate.service.CrateService;

import java.util.Map;

@RequiredArgsConstructor
public class CrateController {


    private final CrateService crateService;
    private final Map<String, Crate> crates;


    public void createCrate(String name, CrateRarity rarity, long cost) {

    }

    public void addItemToCrate(CrateItem crateItem) {

    }

}
