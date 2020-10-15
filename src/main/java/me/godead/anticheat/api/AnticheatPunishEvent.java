package me.godead.anticheat.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AnticheatPunishEvent extends Event implements Cancellable {

	Player p;
	String cheat;
	char type;
	int vl;
	private boolean isCancelled;

	public AnticheatPunishEvent(Player player, String cheat, char type, float vl) {
		p = player;
		this.isCancelled = false;
		this.vl = (int) vl;
		this.cheat = cheat;
		this.type = type;
	}

	
	public Player getPlayer() {
		return p;
	}

	
	public String getCheat() {
		return cheat;
	}

	
	public char getType() {
		return type;
	}

	
	public int getVl() {
		return vl;
	}


	private static final HandlerList handlers = new HandlerList();


	
	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		isCancelled = cancel;
	}

	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
