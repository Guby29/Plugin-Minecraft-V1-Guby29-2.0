package fr.guby.plugin1;


import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

//import fr.guby.plugin1.Task.TimerTask;
import fr.guby.plugin1.commands.CommandAlert;
import fr.guby.plugin1.commands.CommandInfo;
import fr.guby.plugin1.commands.CommandSpawn;
import fr.guby.plugin1.commands.CommandTest;
import fr.guby.plugin1.commands.Commandserver;

public class Main extends JavaPlugin {
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("Plugin Guby Starting");
		getCommand("test").setExecutor(new CommandTest(this));
		getCommand("info").setExecutor(new CommandInfo());
		getCommand("alert").setExecutor(new CommandAlert());
		getCommand("spawn").setExecutor(new CommandSpawn());
		getCommand("server").setExecutor(new Commandserver());
		getServer().getPluginManager().registerEvents(new pluginListeners(this), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		String[] messages = {"§6Bienvenue sur le serveur","§aN'Hesitez pas a rejoidre le Discord §6https://discord.gg/bUy8UMzvVU ", "§aMerci de bien respecter les règles", "§bBon jeu !" };
		
		
		for(String string : getConfig().getConfigurationSection("teleportation").getKeys(false)) {
			System.out.println(getConfig().getConfigurationSection("teleportation").getInt(string + ".id"));
		}
		for(String string : getConfig().getStringList("badwords")){
			System.out.println(string);
			//pour recupérer tout les éléments d'une section
		}
		
		//----------------------------------
		//Va avec la page TimerTask
		
		//TimerTask task = new TimerTask();
		//task.runTaskTimer(this, 0, 20);
		//-------------------------------------
		
		Bukkit.getScheduler().runTaskTimer(this, new BukkitRunnable() {
			
			@Override
			public void run() {
				Bukkit.broadcastMessage(messages[new Random().nextInt(messages.length)]);
				
			}
		}, 0, 20*60);
		

	}
	
	@Override
	public void onDisable() {
		System.out.println("Plugin Guby OFF");
	}
	


}
