package fr.choco70.epicprison.commands;

import fr.choco70.epicprison.EpicPrison;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class blockCommands implements CommandExecutor {

    private FileConfiguration config;
    private EpicPrison plugin;

    public blockCommands(EpicPrison epicprison) {

        this.plugin = epicprison;
        this.config = plugin.getConfig();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player)sender;

            if(label.equalsIgnoreCase("block")) {

                if(args.length > 0){

                    if(args[0].equalsIgnoreCase("set")) {
                        
                        if(args.length == 3){
                            
                            try {

                                int value = Integer.parseInt(args[2]);
                                config.set("block."+ args[1] + ".xp", value);
                                plugin.saveConfig();
                                player.sendMessage("Test réussi !");
                                return true;

                            }

                            catch(NumberFormatException e){

                                player.sendMessage("Erreure NumberFormatException");
                                return false;

                            }
                            
                        }

                    }

                    if(args[0].equalsIgnoreCase("del")) {

                        if(args[1] != null) {

                            return true;

                        }

                    }

                }

            }

        }

        return false;
    }

}
