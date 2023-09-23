package mxzx;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import mxzx.ams.controller.AmsController;
import mxzx.ams.repository.AmsRepository;
import mxzx.ams.service.AmsService;
import mxzx.command.AmsCommand;
import mxzx.command.KitCommand;
import mxzx.command.PerkCommand;
import mxzx.kit.controller.KitController;
import mxzx.kit.repository.KitRepository;
import mxzx.kit.service.KitService;
import mxzx.perk.controller.PerkController;
import mxzx.perk.repository.PerkRepository;
import mxzx.perk.service.PerkService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import mxzx.admin.Controller.GodmodeController;
import mxzx.bounty.controller.BountyController;
import mxzx.bounty.repository.BountyRepository;
import mxzx.bounty.service.BountyService;
import mxzx.chat.controller.GlobalMuteController;
import mxzx.chat.controller.ReplyController;
import mxzx.chat.model.GlobalMute;
import mxzx.clan.controller.ClanController;
import mxzx.clan.repository.ClanRepository;
import mxzx.clan.service.ClanService;
import mxzx.command.AnvilCommand;
import mxzx.command.BackCommand;
import mxzx.command.BlockInfoCommand;
import mxzx.command.BodyseeCommand;
import mxzx.command.BountyCommand;
import mxzx.command.BreakCommand;
import mxzx.command.BroadcastCommand;
import mxzx.command.ClanCommand;
import mxzx.command.EnderchestCommand;
import mxzx.command.ExtCommand;
import mxzx.command.FeedCommand;
import mxzx.command.FixCommand;
import mxzx.command.FlyCommand;
import mxzx.command.GamemodeCommand;
import mxzx.command.GlobalMuteCommand;
import mxzx.command.GodmodeCommand;
import mxzx.command.HealCommand;
import mxzx.command.InvseeCommand;
import mxzx.command.MessageCommand;
import mxzx.command.MoneyCommand;
import mxzx.command.MoreCommand;
import mxzx.command.PayCommand;
import mxzx.command.PunishmentCommand;
import mxzx.command.SkullCommand;
import mxzx.command.StackCommand;
import mxzx.command.StatsCommand;
import mxzx.command.TeamcheatCommand;
import mxzx.command.TrashCommand;
import mxzx.command.VoteCommand;
import mxzx.command.VoucherCommand;
import mxzx.command.WarpCommand;
import mxzx.listener.BlockBreakListener;
import mxzx.listener.BlockPlaceListener;
import mxzx.listener.PlayerJoinListener;
import mxzx.listener.PlayerPvpListener;
import mxzx.listener.PlayerTeleportListener;
import mxzx.metadata.MetadataInfoController;
import mxzx.player.controller.PlayerDataController;
import mxzx.player.repository.PlayerDataRepository;
import mxzx.player.service.PlayerDataService;
import mxzx.punishment.controller.PunishmentController;
import mxzx.punishment.repository.PunishmentRepository;
import mxzx.punishment.service.PunishmentService;
import mxzx.teleport.controller.TeleportController;
import mxzx.voucher.controller.VoucherController;
import mxzx.voucher.service.VoucherService;
import mxzx.warp.controller.WarpController;
import mxzx.warp.repository.WarpRepository;
import mxzx.warp.service.WarpService;

import java.util.ArrayList;

@Getter
@Setter
public class Main extends JavaPlugin {

    public PaperCommandManager manager;
    public static ArrayList<String> openInventory = new ArrayList<>();
    private Main main;
    private MetadataInfoController metadataInfoController;
    private PunishmentController punishmentController;
    private PlayerDataController playerDataController;
    private ReplyController replyController;
    private GodmodeController godmodeController;
    private GlobalMuteController globalMuteController;
    private VoucherController voucherController;
    private WarpController warpController;
    private ClanController clanController;
    private TeleportController teleportController;
    private BountyController bountyController;
    private AmsController amsController;
    private KitController kitController;
    private PerkController perkController;

    @Override
    public void onEnable() {
        main = this;
        initializeAll();
    }

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }

    private void initializeAll() {
        setManager(new PaperCommandManager(this));
        setMetadataInfoController(new MetadataInfoController(this));
        setPunishmentController(new PunishmentController(new PunishmentService(new PunishmentRepository())));
        getPunishmentController().init();
        setPlayerDataController(new PlayerDataController(new PlayerDataService(new PlayerDataRepository())));
        setReplyController(new ReplyController());
        setGodmodeController(new GodmodeController());
        setGlobalMuteController(new GlobalMuteController(new GlobalMute(false)));
        setVoucherController(new VoucherController(new VoucherService()));
        setWarpController(new WarpController(new WarpService(new WarpRepository())));
        getWarpController().setUp();
        setClanController(new ClanController(new ClanService(new ClanRepository())));
        setTeleportController(new TeleportController());
        setBountyController(new BountyController(new BountyService(new BountyRepository())));
        setAmsController(new AmsController(new AmsService(new AmsRepository())));
        setKitController(new KitController(new KitService(new KitRepository())));
        setPerkController(new PerkController(new PerkService(new PerkRepository())));


        getManager().getCommandCompletions().registerStaticCompletion("@punishment-time", new String[]{"perma", "1w", "1h", "1d", "1m"});
        getManager().registerCommand(new GamemodeCommand());
        getManager().registerCommand(new BlockInfoCommand(getMetadataInfoController()));
        getManager().registerCommand(new BreakCommand());
        getManager().registerCommand(new PunishmentCommand(getPunishmentController()));
        getManager().registerCommand(new TrashCommand());
        getManager().registerCommand(new MoreCommand());
        getManager().registerCommand(new ExtCommand());
        getManager().registerCommand(new BroadcastCommand());
        getManager().registerCommand(new EnderchestCommand());
        getManager().registerCommand(new FeedCommand());;
        getManager().registerCommand(new HealCommand());
        getManager().registerCommand(new MoreCommand());
        getManager().registerCommand(new MessageCommand(getReplyController()));
        getManager().registerCommand(new MoneyCommand(getPlayerDataController()));
        getManager().registerCommand(new PayCommand(getPlayerDataController()));
        getManager().registerCommand(new StatsCommand(getPlayerDataController()));
        getManager().registerCommand(new StackCommand());
        getManager().registerCommand(new TeamcheatCommand());
        getManager().registerCommand(new VoteCommand());
        getManager().registerCommand(new FlyCommand());
        getManager().registerCommand(new GodmodeCommand(getGodmodeController()));
        getManager().registerCommand(new InvseeCommand());
        getManager().registerCommand(new BodyseeCommand());
        getManager().registerCommand(new GlobalMuteCommand(getGlobalMuteController()));
        getManager().registerCommand(new VoucherCommand(getVoucherController()));
        getManager().registerCommand(new FixCommand());
        getManager().registerCommand(new AnvilCommand());
        getManager().registerCommand(new SkullCommand());
        getManager().registerCommand(new WarpCommand(getWarpController()));
        getManager().registerCommand(new ClanCommand(getClanController()));
        getManager().registerCommand(new BackCommand(getTeleportController()));
        getManager().registerCommand(new BountyCommand(getPlayerDataController(), getBountyController()));
        getManager().registerCommand(new AmsCommand(getAmsController()));
        getManager().registerCommand(new KitCommand(getKitController()));
        getManager().registerCommand(new PerkCommand(getPerkController()));

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BlockPlaceListener(getMetadataInfoController()), this);
        pm.registerEvents(new BlockBreakListener(getMetadataInfoController()), this);
        pm.registerEvents(new PlayerJoinListener(getPlayerDataController()), this);
        pm.registerEvents(new PlayerPvpListener(getPlayerDataController(), getGodmodeController(), getBountyController()), this);
        pm.registerEvents(new PlayerTeleportListener(getTeleportController()), this);

    }

}
