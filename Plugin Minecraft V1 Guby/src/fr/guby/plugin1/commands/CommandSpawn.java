package fr.guby.plugin1.commands;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandSpawn implements CommandExecutor {
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Location spawn = new Location(player.getWorld(), 23.775, 12, -10.524, 0.3f, -0.3f);
			player.sendMessage("§a Vous avez ete teleporte au spawn !");
			player.teleport(spawn);
			player.getInventory().clear();
			ItemStack customcompass = new ItemStack(Material.COMPASS, 1);
			ItemMeta customM = customcompass.getItemMeta();
			customM.setDisplayName("§2Menu de navigation");
			customM.setLore(Arrays.asList("Passage en Gamemode 3","Se teleporter au spawn"));
			customM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
			customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			customcompass.setItemMeta(customM);
			
			player.getInventory().setItem(4, customcompass);
			player.updateInventory();
		}
		
		
		return false;
	}

}

    