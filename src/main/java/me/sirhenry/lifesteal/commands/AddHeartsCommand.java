package me.sirhenry.lifesteal.commands;

import me.sirhenry.lifesteal.LifeSteal;
import me.sirhenry.lifesteal.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class AddHeartsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int HeartNum;
        Player Receiver;
        Plugin plugin = LifeSteal.getPlugin(LifeSteal.class);


        //check for Correct Number of Args
        if(args.length != 2){
            return false;
        }

        //Set HeartNum var
        try{
            //make sure heart num is whole number
            if(Double.parseDouble(args[0])%1 != 0){
                sender.sendMessage(ChatColor.RED + "You can Only Use Whole Numbers");
                return true;
            }
            HeartNum = Integer.parseInt(args[0])*2;
        }catch(NumberFormatException ex){
            return false;
        }

        //set receiver var
        try{
            Receiver = Bukkit.getPlayer(args[1]);
        }catch(Exception ex){
            sender.sendMessage(ChatColor.RED + "Usage: /AddHearts 10 Player\nThat player may not be online.");
            return true;
        }

        //check if violates max health
        if(Util.getHearts(Receiver) + HeartNum > plugin.getConfig().getDouble("MaxHealth")){
            sender.sendMessage(ChatColor.RED + "This Action Violates the \"Max Hearts\" Parameter.");
            return true;
        }

        Util.setHearts(Receiver, Util.getHearts(Receiver) + HeartNum);

        return true;
    }
}
