package cf.pies.ClientInfo;

import cf.pies.ClientInfo.api.ClientInfoApi;
import cf.pies.ClientInfo.commands.ClientInfoCommand;
import cf.pies.ClientInfo.data.InfoPlayer;
import cf.pies.ClientInfo.listeners.FMLListener;
import cf.pies.ClientInfo.listeners.ModListener;
import cf.pies.ClientInfo.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ClientInfo extends JavaPlugin {
    private static ClientInfoApi api;
    public static ClientInfoApi getApi() {
        return api;
    }

    public HashMap<Player, InfoPlayer> playerMap = new HashMap<>();

    public InfoPlayer getInfoPlayer(Player player) {
        return playerMap.get(player);
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        api = new ClientInfoApi(this);

        FMLListener fmlListener = new FMLListener(this);
        Bukkit.getPluginManager().registerEvents(fmlListener, this);

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ModListener(this), this);

        this.getCommand("clientinfo").setExecutor(new ClientInfoCommand(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getMessenger().unregisterIncomingPluginChannel(this, "FML|HS");
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(this, "FML|HS");
    }
}