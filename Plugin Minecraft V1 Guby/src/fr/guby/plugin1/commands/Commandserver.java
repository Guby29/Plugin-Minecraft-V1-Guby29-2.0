package fr.guby.plugin1.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commandserver implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			player.sendMessage("§6 Bienvenue sur notre serveur Minecraft ! \n \n §3 Ce serveur à été crée par Guby29 et Viande2Mouton en Juin 2023 \n Il s'agit d'un serveur Mini jeux avec sourtout du PVP \n \n Bon Jeu !!");
		}
		
		
		return false;
	}

}
