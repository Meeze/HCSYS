package mxzx.shop.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx._core.Base;
import mxzx.shop.model.Shop;
import mxzx.shop.service.ShopService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class ShopController implements Base {

    private final ShopService shopService;
    private final Map<String, Shop> shops = new HashMap<>();

    public void registerShop(String name, String title) {
        Shop shop = Shop.builder().name(name).displayTitle(title).shopItems(new ArrayList<>()).build();
    }

    public void setUp() {
        getShopService().loadAll().forEach(shop -> getShops().put(shop.getName(), shop));
    }

    private Shop getShop(String name) {
        getShops().putIfAbsent(name, getShopService().load(name));
        return getShops().get(name);
    }

}
