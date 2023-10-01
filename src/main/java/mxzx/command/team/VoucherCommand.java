package mxzx.command.team;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mxzx.voucher.model.ContentType;
import org.bukkit.entity.Player;
import mxzx._core.BasedCommand;
import mxzx.voucher.controller.VoucherController;
import mxzx.voucher.model.Voucher;
import java.time.Instant;
import java.util.UUID;

@CommandAlias("voucher")
@CommandPermission("system.command.voucher")
@RequiredArgsConstructor
@Getter
public class VoucherCommand extends BasedCommand {

    private final VoucherController voucherController;

    @Subcommand("create")
    public void onCreate(Player player) {
        Voucher voucher = Voucher.builder().voucherId(UUID.randomUUID().toString()).createdAt(Instant.now().toString()).creatorId(player.getUniqueId().toString()).amount(10).content(ContentType.SPAWNER).build();
        player.getInventory().addItem(getVoucherController().createVoucherItem(voucher));
        getVoucherController().saveVoucher(voucher);
    }

    @Subcommand("redeem")
    public void onRedeem(Player player) {
        String id = player.getItemInHand().getItemMeta().getLore().get(0);
        getVoucherController().redeem(id, player);
    }


}
