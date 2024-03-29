package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level=AdminLevel.SUPER, source=SourceType.BOTH)
@CommandParameters(description="Clears the Chat", usage="/<command>", aliases="cc")
public class Command_clearchat
  extends TFM_Command
{
  public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
  {
    for (int i = 0; i <= 100; i++) {
      TFM_Util.bcastMsg("");
    }
    TFM_Util.bcastMsg(ChatColor.GOLD + " Chat has been cleared by " + sender.getName() + ".");
    return true;
  }
}
