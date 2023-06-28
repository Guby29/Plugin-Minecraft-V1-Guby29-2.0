package fr.guby.plugin1.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAlert implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player){
			Player player = (Player)sender;
			
			if(args.length == 0) {
				player.sendMessage("la commande est: /alert <message>");
			}
			
			if(args.length >= 1) {
				
				StringBuilder alert = new StringBuilder();
				for(String part : args) {
					alert.append(part + " ");
				}
				
				Bukkit.broadcastMessage("§1[" + player.getName() + "]§6 " + alert.toString());
			}
			
		}
		
		return false;
	}

}
