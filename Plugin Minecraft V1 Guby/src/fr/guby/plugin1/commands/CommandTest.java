package fr.guby.plugin1.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.guby.plugin1.Main;

public class CommandTest implements CommandExecutor {
	
	private Main main;

	public CommandTest(Main main) {
		this.main = main;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {
		
		if(sender instanceof Player){
			Player player = (Player)sender;
			player.sendMessage(main.getConfig().getString("messages.test").replace("&", "§"));
		}
		
		return false;
	}

}
