package me.waffle.api.mysql;

import me.waffle.api.API;
import me.waffle.api.mysql.exceptions.NotConnectedException;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class MySQL {
    private static SQLConnection connection;

    public static void connect() {
        try {
            connection = new SQL()
                    .ip("localhost")
                    .database("serverData")
                    .user("server").password("PASSWORD")
                    .reconnect(true)
                    .build();
            update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        new BukkitRunnable() {
            public void run() {
                try {
                    MySQL.get().query(QueryType.GET, "select uuid from user where name = 'Waffle_02';");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NotConnectedException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(API.get(), 30 * 60 * 20, 30 * 60 * 20);
    }

    public static SQLConnection get() {
        return connection;
    }
}
