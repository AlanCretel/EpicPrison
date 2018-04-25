package fr.choco70.epicprison.commands;

import fr.choco70.epicprison.EpicPrison;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class epicEnchantCommand implements CommandExecutor {

    private FileConfiguration config;
    private EpicPrison plugin;

    public epicEnchantCommand(EpicPrison epicprison) {

        this.plugin = epicprison;
        this.config = plugin.getConfig();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player)sender;
            UUID playerUUID = player.getUniqueId();
            Integer playerXP = Integer.parseInt(config.get("player." + playerUUID + ".points").toString());
            if(label.equalsIgnoreCase("epicenchant") || label.equalsIgnoreCase("eench") || label.equalsIgnoreCase("eenchant")){

                if(args.length == 1){

                    Enchantment enchant = Enchantment.getByName(args[0].toUpperCase());
                    ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

                    if(enchant != null && itemInMainHand.getEnchantmentLevel(enchant) != 0){

                        Integer enchantLevel = itemInMainHand.getEnchantmentLevel(enchant);

                        if(config.get("enchant." + enchant.getName().toLowerCase() + ".cost") != null){

                            Integer enchantCost = Integer.parseInt(config.get("enchant." + enchant.getName().toLowerCase() + ".cost").toString());

                            if(playerXP >= (enchantCost * enchantLevel)){

                                itemInMainHand.addUnsafeEnchantment(enchant, enchantLevel + 1);
                                player.updateInventory();
                                config.set("player." + playerUUID + ".points", playerXP - (enchantCost * enchantLevel));
                                plugin.saveConfig();

                                return true;

                            }
                            else{

                                player.sendMessage("You don't have enougth mining points, you need " + enchantCost * enchantLevel + " mining points!");

                                return true;

                            }
                        }
                    }
                    else{
                        if(enchant != null && config.get("enchant." + enchant.getName().toLowerCase() + ".cost") != null){

                            Integer enchantCost = Integer.parseInt(config.get("enchant." + enchant.getName().toLowerCase() + ".cost").toString());

                            if(playerXP >= enchantCost){
                                itemInMainHand.addUnsafeEnchantment(enchant, 1);
                                player.updateInventory();
                                config.set("player." + playerUUID + ".points", playerXP - enchantCost);
                                plugin.saveConfig();

                                return true;

                            }
                            else{

                                player.sendMessage("You don't have enougth mining points, you need " + enchantCost + " mining points!");

                                return true;

                            }
                        }
                    }

                    return false;

                }

                return true;

            }

        }

        return false;
    }

}
