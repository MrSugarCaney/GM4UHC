package co.gm4.uhc.team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import co.gm4.uhc.Util;

/**
 * A team consists out of multiple players and a team colour.
 * <p>
 * A team must be spread together and friendly fire should be disabled.
 * 
 * @author MrSugarCaney
 */
public class Team {

	/**
	 * Team count making sure all teams have a different ID.
	 */
	private static int idCount = 1;

	/**
	 * The team ID.
	 */
	private int id;

	/**
	 * All the members of the team.
	 */
	private List<UUID> players = new ArrayList<>();

	/**
	 * List of all deaths.
	 */
	private List<UUID> deaths = new ArrayList<>();

	/**
	 * The display colour a team has.
	 * <p>
	 * Default: {@link ChatColor#WHITE}
	 */
	private ChatColor colour = ChatColor.WHITE;

	/**
	 * The ChatColor accent the team has stored as a String.
	 * <p>
	 * E.g. {@link ChatColor#ITALIC}.
	 * <p>
	 * Default: <i>""</i>
	 */
	private String accent = "";

	public Team() {
		id = idCount++;
	}

	/**
	 * Get the list of all team members.
	 */
	public List<UUID> getPlayers() {
		return players;
	}

	/**
	 * Get the list of all team members.
	 */
	public synchronized List<UUID> getAsyncPlayers() {
		return players;
	}

	public void setColour(ChatColor colour) {
		this.colour = colour;
	}

	public void setAccent(ChatColor colour) {
		if (colour == null) {
			accent = "";
			return;
		}

		accent = colour + "";
	}

	public void setAccent(String accent) {
		this.accent = accent;
	}

	/**
	 * Returns the string including a chosen accent.
	 * 
	 * @return colour + accent
	 */
	public synchronized String getChatColours() {
		return colour + accent;
	}

	public ChatColor getColour() {
		return colour;
	}

	public int getId() {
		return id;
	}

	public int size() {
		return players.size();
	}

	public boolean contains(Player player) {
		return players.contains(player.getUniqueId());
	}

	public boolean add(Player player) {
		return players.add(player.getUniqueId());
	}

	/**
	 * Removes a player from the team and adds them to the dead player list.
	 * 
	 * @param player
	 *            The player that dies.
	 */
	public void die(Player player) {
		players.remove(player.getUniqueId());
		deaths.add(player.getUniqueId());
	}

	public boolean remove(Player player) {
		return players.remove(player.getUniqueId());
	}

	public List<UUID> getDeaths() {
		return deaths;
	}

	@Override
	public String toString() {
		return "Team #" + id + ":" + Util.toString(players);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team)obj;
		if (id != other.id)
			return false;
		return true;
	}

}
