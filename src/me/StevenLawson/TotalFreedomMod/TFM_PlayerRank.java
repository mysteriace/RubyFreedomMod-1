package me.StevenLawson.TotalFreedomMod;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.DEVELOPERS;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.SYS;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum TFM_PlayerRank
{
    DEVELOPER("a " + ChatColor.DARK_PURPLE + "Developer", ChatColor.DARK_PURPLE + "[Dev]"),
    IMPOSTOR("an " + ChatColor.YELLOW + ChatColor.UNDERLINE + "Impostor", ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "[IMP]"),
    NON_OP("a " + ChatColor.GREEN + "Non-OP", ChatColor.GREEN.toString()),
    OP("an " + ChatColor.RED + "OP", ChatColor.RED + "[OP]"),
    SUPER("a " + ChatColor.GOLD + "Super Admin", ChatColor.GOLD + "[SA]"),
    TELNET("a " + ChatColor.DARK_GREEN + "Super Telnet Admin", ChatColor.DARK_GREEN + "[STA]"),
    SENIOR("a " + ChatColor.LIGHT_PURPLE + "Senior Admin", ChatColor.LIGHT_PURPLE + "[SrA]"),
    OWNER("the " + ChatColor.BLUE + "Owner", ChatColor.BLUE + "[Owner]"),
    SYS("a " + ChatColor.DARK_RED + "System Admin", ChatColor.DARK_RED + "[SyS]"),
    FOUNDER("the " + ChatColor.DARK_RED + "§4Owner §band §9Founder§b!", ChatColor.DARK_RED + "§8[§4Owner §9+ §9Founder§8]§9"),
    SUSPENDED ("a " + ChatColor.GOLD + "§8Suspended Admin", ChatColor.DARK_RED + "§8[Suspended]"),
    LEADDEV("The " + ChatColor.DARK_PURPLE + "Lead Developer", ChatColor.DARK_PURPLE + "[L.Dev]"),
    EX("a " + ChatColor.YELLOW + "Executive", ChatColor.YELLOW + "[Exec]"),
    ADMINM("The " + ChatColor.DARK_RED + "Admin Manager/System Admin", ChatColor.RED + "[Admin Manager + Sys]"),
    COOWNER("a " + ChatColor.BLUE + "Co Owner", ChatColor.BLUE + "[C.Owner]"),
    CONSOLE("The " + ChatColor.DARK_PURPLE + "Console", ChatColor.DARK_PURPLE + "[Console]");
    private final String loginMessage;
    private final String prefix;

    private TFM_PlayerRank(String loginMessage, String prefix)
    {
        this.loginMessage = loginMessage;
        this.prefix = prefix;
    }

    public static String getLoginMessage(CommandSender sender)
    {
        // Handle console
        if (!(sender instanceof Player))
        {
            return fromSender(sender).getLoginMessage();
        }

        // Handle admins
        final TFM_Admin entry = TFM_AdminList.getEntry((Player) sender);
        if (entry == null)
        {
            // Player is not an admin
            return fromSender(sender).getLoginMessage();
        }

        // Custom login message
        final String loginMessage = entry.getCustomLoginMessage();

        if (loginMessage == null || loginMessage.isEmpty())
        {
            return fromSender(sender).getLoginMessage();
        }

        return ChatColor.translateAlternateColorCodes('&', loginMessage);
    }

    public static TFM_PlayerRank fromSender(CommandSender sender)
    {
        if (!(sender instanceof Player))
        {
            return CONSOLE;
        }

        if (TFM_AdminList.isAdminImpostor((Player) sender))
        {
            return IMPOSTOR;
        }

        if (DEVELOPERS.contains(sender.getName()))
        {
            return DEVELOPER;
        }
        if (sender.getName().equals("cowgomooo12") || sender.getName().equals("olivercricket") || sender.getName().equals("eddieusselman") || sender.getName().equals("xYurippe") || sender.getName().equals("Stampy100"))
        {
            return SYS;
        }
        
        if (sender.getName().equals("Valencia_Orange"))
        {
            return LEADDEV;
        }
        
        if (sender.getName().equals("Alosion") || sender.getName().equals("MysteriAce") || sender.getName().equals("LydiaWolfle") || sender.getName().equals("triplewer") || sender.getName().equals("xBadDawgx") || sender.getName().equals("camille20009"))
        {
            return EX;
        }
        if (TFM_Util.LEADDEV.contains(sender.getName()))
        {
            return LEADDEV;
        }
        if (sender.getName().equals("TaahThePenguin"))
        {
            return COOWNER;
        }
        if (sender.getName().equals("RedEastWood"))
        {
            return SUSPENDED;
        }
        if (sender.getName().equals("DarkGamingDronze") || sender.getName().equals("falceso"))
        {
            return FOUNDER;
        }
        if (sender.getName().equals("NL_Fenix_NL") || sender.getName().equals("jeanluc1998"))
        {
            return ADMINM;
        }

        final TFM_Admin entry = TFM_AdminList.getEntryByIp(TFM_Util.getIp((Player) sender));

        final TFM_PlayerRank rank;

        if (entry != null && entry.isActivated())
        {
            if (TFM_ConfigEntry.SERVER_OWNERS.getList().contains(sender.getName()))
            {
                return OWNER;
            }

            if (entry.isSeniorAdmin())
            {
                rank = SENIOR;
            }
            else if (entry.isTelnetAdmin())
            {
                rank = TELNET;
            }
            else
            {
                rank = SUPER;
            }
        }
        else
        {
            if (sender.isOp())
            {
                rank = OP;
            }
            else
            {
                rank = NON_OP;
            }

        }
        return rank;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getLoginMessage()
    {
        return loginMessage;
    }
}
