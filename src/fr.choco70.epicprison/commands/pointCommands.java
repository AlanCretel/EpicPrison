package fr.choco70.epicprison.commands;

import fr.choco70.epicprison.EpicPrison;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class pointCommands implements CommandExecutor {

    private FileConfiguration config;
    private EpicPrison plugin;

    public pointCommands(EpicPrison epicprison) {

        this.plugin = epicprison;
        this.config = plugin.getConfig();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player)sender;
            UUID playerUUID = player.getUniqueId();
            Integer playerXP = Integer.parseInt(config.get("player." + playerUUID + ".points").toString());
            config.set("player." + playerUUID + ".name", player.getName());
            plugin.saveConfig();
            if(label.equalsIgnoreCase("points")){

                player.sendMessage("You have §4" + playerXP + "§f mining points");

                return true;
            }

        }

        return false;
    }

}
