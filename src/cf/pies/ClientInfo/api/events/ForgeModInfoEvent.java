package cf.pies.ClientInfo.api.events;

import cf.pies.ClientInfo.api.ClientInfoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Map;

public class ForgeModInfoEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    private final Player player;
    private final ClientInfoPlayer infoPlayer;


    public ForgeModInfoEvent(Player player, ClientInfoPlayer infoPlayer) {
        this.player = player;
        this.infoPlayer = infoPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public ClientInfoPlayer getInfoPlayer() {
        return infoPlayer;
    }

    public Map<String, String> getMods() {
        return this.getInfoPlayer().getFMLMods();
    }
}
