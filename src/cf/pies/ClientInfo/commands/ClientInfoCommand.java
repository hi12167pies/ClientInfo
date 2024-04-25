package cf.pies.ClientInfo.commands;

import cf.pies.ClientInfo.ClientInfo;
import cf.pies.ClientInfo.data.InfoPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClientInfoCommand implements CommandExecutor {
    private final ClientInfo plugin;
    public ClientInfoCommand(ClientInfo plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("§cUsage: " + command.getUsage());
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return true;
        }

        InfoPlayer infoPlayer = plugin.getInfoPlayer(target);

        if (infoPlayer == null) {
            sender.sendMessage("§cInfoPlayer does not exist yet. The player must rejoin.");
            return true;
        }

        sender.sendMessage("§7§m------------------------------");
        sender.sendMessage("§f" + target.getDisplayName() + "§a's client info:");
        sender.sendMessage("§aClient§7: " + infoPlayer.clientName);
        if (infoPlayer.isModSupported) {
            sender.sendMessage("§aMods§7 (" + infoPlayer.mods.size() + "): ");
            for (String mod : infoPlayer.mods) {
                sender.sendMessage("§7- §f" + mod);
            }
        } else {
            sender.sendMessage("§aMods§7: Player is not on client with mods.");
        }
        sender.sendMessage("§7§m------------------------------");

        return true;
    }
}
