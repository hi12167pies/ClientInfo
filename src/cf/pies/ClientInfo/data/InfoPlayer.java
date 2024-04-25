package cf.pies.ClientInfo.data;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class InfoPlayer {
    private final Player player;
    public InfoPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String clientName = "Unknown";
    public List<String> mods = Collections.emptyList();
    public boolean isModSupported = false;
}
