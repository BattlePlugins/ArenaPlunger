package mc.adambor.ArenaPlunger;

import mc.alk.arena.BattleArena;
import mc.alk.arena.objects.victoryconditions.VictoryType;
import mc.alk.arena.util.Log;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class ArenaPlungerPlugin extends JavaPlugin {

	private static ArenaPlungerPlugin plugin;

    @Override
    public void onEnable(){
    	plugin = this;
        VictoryType.register(Victory.class, this);
        BattleArena.registerCompetition(this, "ArenaPlunger", "ap", ArenaPlunger.class, new PlungerExecutor(this));
        loadConfig();
        Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " enabled!");
    }

    @Override
    public void onDisable(){
        Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " stopping!");
    }
    public void loadConfig(){
        saveDefaultConfig();
        FileConfiguration conf = getConfig();
        ArenaPlunger.material = Material.valueOf(conf.getString("plunger.material", "TORCH").toUpperCase());
        ArenaPlunger.effect = Effect.valueOf(conf.getString("plunger.effect.type", "note").toUpperCase());
        ArenaPlunger.maxdroppedtime = conf.getInt("plunger.timer", 30);
        ArenaPlunger.pointsToWin = conf.getInt("scoreToWin", 100);
        ArenaPlunger.count = conf.getInt("plunger.effect.count", 10);
    }
    @Override
    public void reloadConfig(){
        super.reloadConfig();
        loadConfig();
    }

    public static ArenaPlungerPlugin getPlugin() {
    	return plugin;
	}
}
