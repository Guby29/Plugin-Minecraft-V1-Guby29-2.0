package fr.guby.plugin1.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInfo implements CommandExecutor {

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {
		
		if(sender instanceof Player){
			Player player = (Player)sender;
			player.sendMessage("§6Bienvenue \n §3Plugin cree par Guby29 \n Version 1.0");
		}
		
		return false;
	}

}
