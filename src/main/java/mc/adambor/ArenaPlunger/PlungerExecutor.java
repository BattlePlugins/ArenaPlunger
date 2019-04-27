package mc.adambor.ArenaPlunger;

import org.bukkit.Effect;
import org.bukkit.entity.Player;

import mc.alk.arena.BattleArena;
import mc.alk.arena.executors.CustomCommandExecutor;
import mc.alk.arena.executors.MCCommand;

public class PlungerExecutor extends CustomCommandExecutor {

    private ArenaPlungerPlugin plugin;

    public PlungerExecutor(ArenaPlungerPlugin plugin) {
        this.plugin = plugin;
    }

    @MCCommand(cmds={"setPlunger"}, admin=true)
    public boolean addPlunger(Player sender, ArenaPlunger arena) {
        arena.setPlunger(sender.getLocation());
        BattleArena.saveArenas(plugin);
        return sendMessage(sender,"&2Plunger set!");
    }

    @MCCommand(cmds={"setMaterial"}, admin=true)
    public boolean setMaterial(Player sender){
        String material = sender.getItemInHand().getType().name();
        plugin.getConfig().set("plunger.material", material);
        plugin.saveConfig();
        plugin.reloadConfig();
        return sendMessage(sender, "&2Plunger item set to: "+material);
    }

    @MCCommand(cmds={"setTimer"}, admin=true)
    public boolean setTimer(Player sender, Integer time){
        plugin.getConfig().set("plunger.timer", time);
        plugin.saveConfig();
        plugin.reloadConfig();
        return sendMessage(sender, "&2Plunger timer set to: "+time);
    }

    @MCCommand(cmds={"setScoreToWin"}, admin=true)
    public boolean setScoreToWin(Player sender, Integer score){
        plugin.getConfig().set("scoreToWin", score);
        plugin.saveConfig();
        plugin.reloadConfig();
        return sendMessage(sender, "&2Score to win this game is now: "+score);
    }

    @MCCommand(cmds={"setEffect"}, admin=true)
    public boolean setEffect(Player sender, String effect, Integer count){
        if(Effect.valueOf(effect.toUpperCase())!=null){
            plugin.getConfig().set("plunger.effect.type", effect.toUpperCase());
            plugin.getConfig().set("plunger.effect.count", count);
            plugin.saveConfig();
            plugin.reloadConfig();
            return sendMessage(sender, "&2Plunger effect set to: "+effect.toUpperCase()+" and particles count to: "+count);
        } else {
            return sendMessage(sender, "&cThis effect doesn't exist");
        }
    }
}
