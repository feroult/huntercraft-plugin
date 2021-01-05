package huntercraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

public class JoinEvent implements Listener {

    Logger logger = Logger.getLogger("Minecraft");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("Hello HunterCraft!!!");
        Player player = event.getPlayer();
        Object version = getVersion(player);
        logger.info("PLAYER VERSION: " + version);
    }

    private Object getVersion(Player player) {
        try {
            Class<?> playerClazz = player.getClass();
            Method getHandleMethod = playerClazz.getMethod("getHandle");
            Object handle = getHandleMethod.invoke(player);

            Field playerConnectionField = handle.getClass().getDeclaredField("playerConnection");
            Object playerConnection = playerConnectionField.get(handle);

            Field networkManagerField = playerConnection.getClass().getDeclaredField("networkManager");
            Object networkManager = networkManagerField.get(playerConnection);

            Method getVersionMethod = networkManager.getClass().getMethod("getVersion");
            return getVersionMethod.invoke(networkManager);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

}