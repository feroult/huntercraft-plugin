package huntercraft;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Don't forget to create documentation!
 */
public class HunterCraftPlugin extends JavaPlugin {

    Logger logger = Logger.getLogger("Minecraft");

    public void onEnable() {
        PluginDescriptionFile pdfFile = getDescription();
        logger.info(pdfFile.getName() + "has been enabled! Version:" + pdfFile.getVersion());
        registerEvents();
    }

    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");

        logger.info(pdfFile.getName() + "has been disabled! Version:" + pdfFile.getVersion());

    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
    }
}
