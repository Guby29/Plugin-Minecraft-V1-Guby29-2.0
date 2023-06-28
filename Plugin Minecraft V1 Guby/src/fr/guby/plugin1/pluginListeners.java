package fr.guby.plugin1;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class pluginListeners implements Listener {
	
	private Main main;


	public pluginListeners(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		//player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
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
		
		Location spawn = new Location(player.getWorld(), 23.775, 12, -10.524, 0.3f, -0.3f);
		player.teleport(spawn);
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		
		if(event.getClickedBlock() != null && action == Action.RIGHT_CLICK_BLOCK) {
			
			BlockState bs = event.getClickedBlock().getState();
			if(bs instanceof Sign) {
				
				Sign sign = (Sign) bs;
				
				if(sign.getLine(0).equalsIgnoreCase("[Clear]") && sign.getLine(1).equalsIgnoreCase("all")) {
					player.getInventory().clear();
					player.sendMessage("§aVous venez d'etre clear !");
				}
				
				if(sign.getLine(0).equalsIgnoreCase("[Teleport]") && sign.getLine(1).equalsIgnoreCase("Bungee")) {
					
					if(sign.getLine(2) != null) {
						String serverName = sign.getLine(2);
						
						ByteArrayOutputStream b = new ByteArrayOutputStream();
						DataOutputStream out = new DataOutputStream(b);
						
						try {
							out.writeUTF("Connect");
							out.writeUTF(serverName);
						}catch(IOException e) {
							e.printStackTrace();
						}
						
						player.sendPluginMessage(main, "BungeeCord", b.toByteArray());
						
						
					}
				}
			}
		}
		
		
		if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§2Menu de navigation")) {
			
			Inventory inv = Bukkit.createInventory(null, 27, "§8Menu de navigation");
			player.sendMessage("§a Vous venez d ouvrir le menu de naviagtion");
			
			inv.setItem(11, getItem(Material.APPLE, "§ePassage en Gamemode 3"));
			inv.setItem(15, getItem(Material.ANVIL, "§aSe teleporter au spawn"));
			inv.setItem(13, getItem(Material.IRON_SWORD, "§cSe téléporter au mini jeu"));
			
			player.openInventory(inv);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
				
		if(current == null) return;
		
		if(inv.getName().equalsIgnoreCase("§8Menu de navigation")) {
			
			event.setCancelled(true);
			player.closeInventory();
			
			if(current.getType() == Material.APPLE) {
				player.setGameMode(GameMode.SPECTATOR);
			}
			
			if(current.getType() == Material.ANVIL) {
				Location spawn = new Location(player.getWorld(), 23.775, 12, -10.524, 0.3f, -0.3f);
				player.sendMessage("§a Vous avez ete teleporte au spawn !");
				player.teleport(spawn);
			}
			
			if(current.getType() == Material.IRON_SWORD) {
				Location castle = new Location(player.getWorld(), 23.241, 8.0, -67.282, -180.0f,3.4f);
				player.sendMessage("§a Vous avez été télepoté !");
				player.teleport(castle);
				player.getInventory().clear();
				
				ItemStack customironsword = new ItemStack(Material.IRON_SWORD, 1);
				ItemMeta customISwordM = customironsword.getItemMeta();
				customISwordM.setDisplayName("§2Menu du mini jeu");
				customISwordM.setLore(Arrays.asList("Choisir son équipe §4Rouge §rOu §1Bleu","Se téléporter au spawn"));
				customISwordM.addEnchant(Enchantment.ARROW_FIRE, 200, true);
				customISwordM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				customISwordM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				customironsword.setItemMeta(customISwordM);
				
				player.getInventory().setItem(4, customironsword);
				player.updateInventory();
			}
			
			}
			
		}
		
		
	
	
	public ItemStack getItem(Material material, String customName) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		if(customName != null) itM.setDisplayName(customName);
		it.setItemMeta(itM);
		return it;
	}

}	
