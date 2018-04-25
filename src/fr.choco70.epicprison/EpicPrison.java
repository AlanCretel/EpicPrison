package fr.choco70.epicprison;

import fr.choco70.epicprison.commands.epicEnchantCommand;
import fr.choco70.epicprison.commands.epicReloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

import fr.choco70.epicprison.commands.blockCommands;
import fr.choco70.epicprison.commands.pointCommands;
import fr.choco70.epicprison.listeners.BlockBreakListener;
import fr.choco70.epicprison.listeners.PlayerConnectListener;

public class EpicPrison extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        System.out.println("[EpicPrison] Plugin enabled");
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectListener(), this);
        getCommand("points").setExecutor(new pointCommands(this));
        getCommand("block").setExecutor(new blockCommands(this));
        getCommand("epicenchant").setExecutor(new epicEnchantCommand(this));
        getCommand("epicreload").setExecutor(new epicReloadCommand(this));

    }

    @Override
    public void onDisable() {
        System.out.println("[EpicPrison] Plugin disabled");
    }

}
