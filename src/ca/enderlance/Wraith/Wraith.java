package ca.enderlance.Wraith;

import java.util.logging.Logger;

import me.Blanclour.AmestriaRaceCore.AmestriaRaceCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Wraith extends JavaPlugin implements Listener
{
	public static Wraith plugin;
	public final Logger logger = Logger.getLogger("Minecraft");

	public void onEnable()
	{
		org.bukkit.plugin.PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " is ready to reap you!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
	}
	
	public void onDisable() 
	{
		org.bukkit.plugin.PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " has been disabled.");
		saveConfig();
	}
	
	public boolean reapSoulPlayer(Player player, Player tplayer, double damage)
	{
		if (AmestriaRaceCore.getRace(player).equalsIgnoreCase("Wraith"))
		{
	        if(damage >= 20.00)
	        {
	        	damage = 19.00;
	        }
	    	player.sendMessage(ChatColor.DARK_GRAY + "You have reaped " + tplayer.getName() + "'s soul.");
	        double heal = damage/2;
	        double playerHealth = player.getHealth();
	        tplayer.damage(damage);
	        if(playerHealth + heal > 20)
	        {
	        	player.setHealth(20.00);
	        }
	        else if(playerHealth + heal <= 20.00)
	        {
	        	player.setHealth(playerHealth + heal);	
	        }
	    }
	    return true;
	}
	
	@EventHandler
	public void onRightClickEntity(PlayerInteractEntityEvent e)
	{
		Player player = (Player) e.getPlayer();
		if (AmestriaRaceCore.getRace(player).equalsIgnoreCase("Wraith"))
		{
			if(e.getRightClicked() instanceof LivingEntity)
			{
				LivingEntity tentity = (LivingEntity) e.getRightClicked();
				drainEntity(player, tentity, 1.00);
			}
			else if(e.getRightClicked() instanceof Player)
			{
				Player tplayer = (Player) e.getRightClicked();
				drainEntity(player, tplayer, 1.00);
			}
		}
	}
	
	@Deprecated
	@EventHandler
	public void onRightClickPlayer(PlayerInteractEntityEvent e)
	{
		Player player = (Player) e.getPlayer();
		if(e.getRightClicked() instanceof Player)
		{
			Player tplayer = (Player) e.getRightClicked();
			drainPlayer(player, tplayer, 1.00);	
		}
	}
	
	public void drainEntity(Player player, LivingEntity entity, double damage)
	{
		if (player.hasPermission("wraith.drain1") && !player.isOp())
		{
			if ((entity instanceof LivingEntity))
			{
				damage = damage*4;
				if((entity.getHealth() - damage) < 0)
				{
					damage = entity.getHealth()-0.1;
				}
				entity.damage(damage);
				entity.damage(0);
				player.setHealth(player.getHealth() + damage/2);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
			if(entity instanceof Player)
			{
				damage = damage*4;
				if((entity.getHealth() - damage) < 0)
				{
					damage = entity.getHealth()-0.1;
				}
				entity.damage(damage);
				((Player) entity).setFoodLevel(((Player) entity).getFoodLevel()-1);
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
				entity.damage(0);
				player.setHealth(player.getHealth() + damage/2);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
		}
		else if (player.hasPermission("wraith.drain2") && !player.isOp())
		{
			if ((entity instanceof LivingEntity))
			{
				damage = damage*8;
				if((entity.getHealth() - damage) < 0)
				{
					damage = entity.getHealth()-0.1;
				}
				entity.damage(damage);
				entity.damage(0);
				player.setHealth(player.getHealth() + damage/2);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
			if(entity instanceof Player)
			{
				damage = damage*8;
				if((entity.getHealth() - damage) < 0)
				{
					damage = entity.getHealth()-0.1;
				}
				entity.damage(damage);
				((Player) entity).setFoodLevel(((Player) entity).getFoodLevel()-1);
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
				entity.damage(0);
				player.setHealth(player.getHealth() + damage/2);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
		}
		else if (player.hasPermission("wraith.drain3"))
		{
			if ((entity instanceof LivingEntity))
			{
				damage = damage*12;
				if((entity.getHealth() - damage) < 0)
				{
					damage = entity.getHealth()-0.1;
				}
				entity.damage(damage);
				entity.damage(0);
				player.setHealth(player.getHealth() + damage/2);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
			else if(entity instanceof Player)
			{
				damage = damage*12;
				if((entity.getHealth() - damage) < 0)
				{
					damage = entity.getHealth()-0.1;
				}
				entity.damage(damage);
				((Player) entity).setFoodLevel(((Player) entity).getFoodLevel()-1);
				entity.damage(0);
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
				player.setHealth(player.getHealth() + damage/2);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
		}
		
	}
	
	@Deprecated
	public void drainPlayer(Player player, Player tplayer, double damage)
	{
		if (player.hasPermission("wraith.drain1.player") && !player.isOp())
		{
			if(tplayer instanceof Player)
			{
				tplayer.setHealth(tplayer.getHealth() - damage*2);
				player.setHealth(player.getHealth() + damage);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
		}
		else if (player.hasPermission("wraith.drain2.player") && !player.isOp())
		{
			if(player instanceof Player)
			{
				tplayer.setHealth(tplayer.getHealth() - damage*4);
				player.setHealth(player.getHealth() + damage*2);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
		}
		else if (player.hasPermission("wraith.drain3.player"))
		{
			if(player instanceof Player)
			{
				tplayer.setHealth(tplayer.getHealth() - damage*6);
				player.setHealth(player.getHealth() + damage*3);
				player.sendMessage(ChatColor.DARK_GRAY + "You have devoured a soul.");
			}
		}
	}
	
	@EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event)
	{
		Player[] players;
		Player player = (Player) event.getPlayer();
		if(AmestriaRaceCore.getRace(player).equalsIgnoreCase("Wraith") && player.hasPermission("wraith.dissipate.sneak"))
		{
			players = getServer().getOnlinePlayers();
			if(event.isSneaking() == true && player.isFlying()==false)
			{
				player.hidePlayer(player);
				for(int i = 0; i< players.length; i++)
				{
					players[i].hidePlayer(player);
				}
			}
			else
			{
				player.showPlayer(player);
				for(int i = 0; i< players.length; i++)
				{
					players[i].showPlayer(player);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerToggleSprint(PlayerToggleSprintEvent event)
	{
		Player[] players;
		Player player = (Player) event.getPlayer();
		if(AmestriaRaceCore.getRace(player).equalsIgnoreCase("Wraith") && player.hasPermission("wraith.dissipate.run"))
		{
			players = getServer().getOnlinePlayers();
			if(event.isSprinting()==true)
			{
				player.hidePlayer(player);
				for(int i = 0; i< players.length; i++)
				{
					players[i].hidePlayer(player);
				}
			}
			else
			{
				player.showPlayer(player);
				for(int i = 0; i< players.length; i++)
				{
					players[i].showPlayer(player);
				}
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player) sender;
		if(label.equalsIgnoreCase("w"))
		{
			if(AmestriaRaceCore.getRace(player).equalsIgnoreCase("Wraith"))
			{
				if(args.length == 0)
				{
					player.sendMessage(ChatColor.DARK_GRAY + "-------- WRAITH --------");
					player.sendMessage("");
					//player.sendMessage(ChatColor.DARK_RED + "/w show " + ChatColor.DARK_GRAY + "-- Display all wraiths.");
					player.sendMessage(ChatColor.DARK_RED + "/w reap <player> " + ChatColor.DARK_GRAY + "-- Steal health from a specified player.");
					player.sendMessage("");
				}
				else if(args.length == 1)
				{
					if(args[0].equalsIgnoreCase("show") || args[0].equalsIgnoreCase("s"))
					{
						player.sendMessage(ChatColor.RED + "Not implemented yet.");
					}
				}
				else if(args.length == 2)
				{
					if(args[0].equalsIgnoreCase("reap") || args[0].equalsIgnoreCase("r"))
					{
						Player tplayer = player.getServer().getPlayer(args[1]);
				        double damage = player.getLevel()/10;
						reapSoulPlayer(player, tplayer, damage);
					}
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "Error: " + ChatColor.DARK_GRAY + "You do not have permission to do that.");
			}
		}
		return true;
	}
}
