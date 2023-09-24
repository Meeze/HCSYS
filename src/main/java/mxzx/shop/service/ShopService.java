package mxzx.shop.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mxzx.database.Repository;
import mxzx.shop.model.Shop;
import mxzx.shop.model.ShopItem;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class ShopService {

    private final Repository<Shop> shopRepository;
    private final Repository<ShopItem> itemRepository;

    public void saveShopItem(ShopItem item) {
        getItemRepository().save(item);
    }

    public void saveShop(Shop shop) {
        getShopRepository().save(shop);
    }

    public Shop load(String id) {
       return getShopRepository().load(id);
    }

    public List<Shop> loadAll() {
        return getShopRepository().loadAll();
    }

}
