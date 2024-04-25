package cf.pies.ClientInfo.listeners;

import cf.pies.ClientInfo.ClientInfo;
import cf.pies.ClientInfo.api.events.ForgeModInfoEvent;
import cf.pies.ClientInfo.api.events.LabymodAddonInfoEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModListener implements Listener {
    private final ClientInfo plugin;
    public ModListener(ClientInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void event(ForgeModInfoEvent event) {
        Map<String, String> mods = event.getInfoPlayer().getFMLMods();
        List<String> blacklistedMods = plugin.getConfig().getStringList("blacklist.fml");
        for (String blacklistedMod : blacklistedMods) {
            if (mods.containsKey(blacklistedMod)) {
                this.kick(event.getPlayer(), blacklistedMod);
                break;
            }
        }
    }

    @EventHandler
    public void event(LabymodAddonInfoEvent event) {
        Set<String> mods = event.getInfoPlayer().getLabymodAddons();
        List<String> blacklistedMods = plugin.getConfig().getStringList("blacklist.labymod");
        for (String blacklistedMod : blacklistedMods) {
            if (mods.contains(blacklistedMod)) {
                this.kick(event.getPlayer(), blacklistedMod);
                break;
            }
        }
    }

    private void kick(Player player, String mod) {
        player.kickPlayer(
                ChatColor.translateAlternateColorCodes(
                        '&', plugin.getConfig().getString("blacklist.kick_message")
                                .replaceAll("%mod%", mod)
                )
        );
    }
}
