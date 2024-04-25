package cf.pies.ClientInfo.api;

import cf.pies.ClientInfo.ClientInfo;
import org.bukkit.entity.Player;

public class ClientInfoApi {
    private final ClientInfo plugin;
    public ClientInfoApi(ClientInfo plugin) {
        this.plugin = plugin;
    }
    public ClientInfoPlayer getInfoPlayer(Player player) {
        return plugin.getInfoPlayer(player);
    }
}
