package mc.adambor.ArenaPlunger;

import org.bukkit.Effect;
import org.bukkit.entity.Player;

import mc.alk.arena.BattleArena;
import mc.alk.arena.executors.CustomCommandExecutor;
import mc.alk.arena.executors.MCCommand;

public class AP_commandHandler extends CustomCommandExecutor {
    @MCCommand(cmds={"setPlunger"}, admin=true)
    public static boolean addPlunger(Player sender, ArenaPlunger arena) {
        arena.setPlunger(sender.getLocation());
        BattleArena.saveArenas(Main.plugin);
        return sendMessage(sender,"&2Plunger set!");
    }
    @MCCommand(cmds={"setMaterial"}, admin=true)
    public static boolean setMaterial(Player sender){
        String material = sender.getItemInHand().getType().name();
        Main.plugin.getConfig().set("plunger.material", material);
        Main.plugin.saveConfig();
        Main.plugin.reloadConfig();
        return sendMessage(sender, "&2Plunger item set to: "+material);
    }
    @MCCommand(cmds={"setTimer"}, admin=true)
    public static boolean setTimer(Player sender, Integer time){
        Main.plugin.getConfig().set("plunger.timer", time);
        Main.plugin.saveConfig();
        Main.plugin.reloadConfig();
        return sendMessage(sender, "&2Plunger timer set to: "+time);
    }
    @MCCommand(cmds={"setScoreToWin"}, admin=true)
    public static boolean setScoreToWin(Player sender, Integer score){
        Main.plugin.getConfig().set("scoreToWin", score);
        Main.plugin.saveConfig();
        Main.plugin.reloadConfig();
        return sendMessage(sender, "&2Score to win this game is now: "+score);
    }
    @MCCommand(cmds={"setEffect"}, admin=true)
    public static boolean setEffect(Player sender, String effect, Integer count){
        if(Effect.valueOf(effect.toUpperCase())!=null){
            Main.plugin.getConfig().set("plunger.effect.type", effect.toUpperCase());
            Main.plugin.getConfig().set("plunger.effect.count", count);
            Main.plugin.saveConfig();
            Main.plugin.reloadConfig();
            return sendMessage(sender, "&2Plunger effect set to: "+effect.toUpperCase()+" and particles count to: "+count);
        } else {
            return sendMessage(sender, "&cThis effect doesn't exist");
        }
    }
}
