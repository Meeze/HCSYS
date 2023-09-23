package mxzx;


import lombok.Getter;
import mxzx.ams.model.Ams;
import mxzx.bounty.model.Bounty;
import mxzx.kit.model.Kit;
import mxzx.perk.model.Perk;
import mxzx.voucher.model.Voucher;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import mxzx.clan.model.Clan;
import mxzx.clan.model.ClanMember;
import mxzx.clan.model.ClanWarp;
import mxzx.player.model.PlayerData;
import mxzx.punishment.model.Punishment;
import mxzx.warp.model.Warp;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").applySetting("hibernate.hbm2ddl.auto", "update").build();
            Metadata metadata = new MetadataSources(standardRegistry).addAnnotatedClass(Punishment.class).addAnnotatedClass(PlayerData.class).addAnnotatedClass(Warp.class).addAnnotatedClass(Clan.class).
                    addAnnotatedClass(ClanMember.class).addAnnotatedClass(ClanWarp.class).addAnnotatedClass(Ams.class).addAnnotatedClass(Bounty.class).addAnnotatedClass(Voucher.class).addAnnotatedClass(Kit.class)
                    .addAnnotatedClass(Perk.class).getMetadataBuilder()
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}