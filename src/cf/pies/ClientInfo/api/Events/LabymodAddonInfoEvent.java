package cf.pies.ClientInfo.api.Events;

import cf.pies.ClientInfo.api.ClientInfoPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Map;
import java.util.Set;

public class LabymodAddonInfoEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private final Player player;
    private final ClientInfoPlayer infoPlayer;


    public LabymodAddonInfoEvent(Player player, ClientInfoPlayer infoPlayer) {
        this.player = player;
        this.infoPlayer = infoPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public ClientInfoPlayer getInfoPlayer() {
        return infoPlayer;
    }
    public Set<String> getAddons() {
        return this.getInfoPlayer().getLabymodAddons();
    }
}
