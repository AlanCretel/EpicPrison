package fr.choco70.epicprison.commands;

import fr.choco70.epicprison.EpicPrison;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class epicReloadCommand implements CommandExecutor {

    private FileConfiguration config;
    private EpicPrison plugin;

    public epicReloadCommand(EpicPrison epicprison) {

        this.plugin = epicprison;
        this.config = plugin.getConfig();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("ereload") || label.equalsIgnoreCase("erl") || label.equalsIgnoreCase("epicreload")){

            plugin.reloadConfig();

            return true;

        }

        return false;
    }

}
