package mxzx.voucher.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mxzx.voucher.model.ContentType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import mxzx.abstraccc.ItemBuilder;
import org.bukkit.inventory.ItemStack;
import mxzx.voucher.model.Voucher;
import mxzx.voucher.service.VoucherService;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VoucherController {

    private VoucherService voucherService;

    public void redeem(String voucherId, Player player) {
        Voucher voucher = voucherService.load(voucherId);
        player.sendMessage(voucher.getContent() + " " + voucher.getAmount());
    }

    public ItemStack createVoucherItem(Voucher voucher) {
        return new ItemBuilder().setMaterial(Material.PAPER).setName(voucher.getContent() + " Amount: " + voucher.getAmount()).addLore(voucher.getVoucherId().toString()).setAmount(1).getItem();
    }

    public void saveVoucher(Voucher voucher) {
        getVoucherService().save(voucher);
    }

}
