package me.godead.anticheat.api;

import me.godead.anticheat.check.Check;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AnticheatFlagEvent extends Event implements Cancellable {

    Player p;
    String cheat;
    char type;
    int vl;

    Check check;

    private boolean isCancelled;

    public AnticheatFlagEvent(Player player, String cheat, char type, float vl, Check check) {
        p = player;
        this.isCancelled = false;
        this.vl = (int) vl;
        this.cheat = cheat;
        this.type = type;
        this.check = check;
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

    
    public Check getCheck() {
        return check;
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
