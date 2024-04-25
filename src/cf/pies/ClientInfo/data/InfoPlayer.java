package cf.pies.ClientInfo.data;

import cf.pies.ClientInfo.api.ClientInfoPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class InfoPlayer implements ClientInfoPlayer {
    private final Player player;
    public InfoPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String clientName = "Unknown";
    // Name, Version
    public Map<String, String> fmlMods = new HashMap<>();
    public Set<String> labymodAddons = new HashSet<>();
    // Api implementation
    @Override
    public Map<String, String> getFMLMods() {
        return fmlMods;
    }

    @Override
    public Set<String> getLabymodAddons() {
        return labymodAddons;
    }

    @Override
    public String getClientName() {
        return this.clientName;
    }

    @Override
    public int getPing() {
        return ((CraftPlayer) this.player).getHandle().ping;
    }

    @Override
    public String getIpAddress() {
        return this.player.getAddress().getAddress().toString();
    }
}
