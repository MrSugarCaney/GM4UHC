package co.gm4.uhc;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.gm4.uhc.chat.Broadcast;

/**
 * Teleport to the other world.
 * <p>
 * Usage: /switch
 * 
 * @author MrSugarCaney
 */
public class SwitchCommand implements CommandExecutor {

	/**
	 * Main plugin instance.
	 */
	private GM4UHC plugin;

	public SwitchCommand(GM4UHC plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Broadcast.ERROR_PREFIX + "Only players can perform this command.");
			return true;
		}

		String lobbyName = plugin.getConfig().getString("lobby-name");
		String worldName = plugin.getConfig().getString("world-name");

		Player player = (Player)sender;
		String world = player.getWorld().getName();

		// Tp to world.
		if (world.equalsIgnoreCase(lobbyName)) {
			Location loc = plugin.getServer().getWorld(worldName).getSpawnLocation();
			player.teleport(loc);
			player.sendMessage(Broadcast.SUCCESS_PREFIX + "You have been teleported to the arena.");
		}
		// Tp to lobby.
		else {
			player.teleport(plugin.getLobby());
			player.sendMessage(Broadcast.SUCCESS_PREFIX + "You have been teleported to the lobby.");
		}

		return true;
	}

}
