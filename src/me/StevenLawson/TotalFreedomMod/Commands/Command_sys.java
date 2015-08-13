package me.StevenLawson.TotalFreedomMod.Commands;

import static me.StevenLawson.TotalFreedomMod.Commands.Command_smite.smite;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH)
@CommandParameters(
        description = "A command for system admins only",
        usage = "/<command> [add <player> | del <player> | teston | testoff]")
public class Command_sys extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole){
    	
          
        if (!TFM_Util.SYS.contains(sender.getName()) && !TFM_Util.DEVELOPERS.contains(sender.getName()) && !TFM_Util.COOWNER.contains(sender.getName()) &&  !TFM_Util.LEADDEV.contains(sender.getName()))
        {
            playerMsg(TFM_Command.MSG_NO_PERMS);
            TFM_Util.adminAction("WARNING: " + sender.getName(), "Has attempted to use a system admin only command. System administration team has been alerted.", true);
            smite(sender_p);
            playerMsg("You have been denied to this command because your rank is lower then System-Admin");
            //lol smites them if they cant do /sys i'm really evil :)
            return true;
        } 
        if (args.length == 0)
        {
            return false;
        }

        String mode = args[0].toLowerCase();

        if (mode.equals("add"))
        {
        	Player player = getPlayer(args[1]);
        	if (player == null){
        		sender.sendMessage(TFM_Command.PLAYER_NOT_FOUND);
        	}
        	TFM_Util.adminAction(sender.getName(), "Adding " + args[1] + " to the superadmin list", true);
        	TFM_AdminList.addSuperadmin(player);
        }
        
        if (mode.equals("del"))
        {
        	Player player = getPlayer(args[1]);
        	if (player == null){
        		sender.sendMessage(TFM_Command.PLAYER_NOT_FOUND);
        	}
        	TFM_Util.adminAction(sender.getName(), "Removing " + args[1] + " from the superadmin list", true);
        	TFM_AdminList.removeSuperadmin(player);
        }
        
        if (mode.equals("teston"))
        {
        	TFM_Util.bcastMsg(ChatColor.RED + "WARNING: " + sender.getName() + " has started testing on this server.");
        }
        
        if (mode.equals("testoff"))
        {
        	TFM_Util.bcastMsg(ChatColor.RED + sender.getName() + " has successfully tested on this server.");
        }
        
        return true;
    }  
}