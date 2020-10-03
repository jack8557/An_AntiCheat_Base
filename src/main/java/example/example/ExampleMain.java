package example.example;

import me.godead.anticheat.config.AntiCheatConfig;
import me.godead.anticheat.extensions.Extensions;
import me.godead.anticheat.plugin.AntiCheatPlugin;
import example.example.checks.ExampleCheck;
import example.example.playerdata.PlayerData;
import org.bukkit.plugin.Plugin;

public class ExampleMain extends AntiCheatPlugin {

    public static Plugin plugin;

    public static AntiCheatConfig myConfig;


    @Override
    public void onStart() {
        setUser(PlayerData.class);
        Extensions.registerCheck(new ExampleCheck());
    }

    @Override
    public void onStartFinish() {
        plugin = getPlugin();
        //myConfig = new ExampleConfig("ExampleConfig").createConfig();
    }

    @Override
    public void onStop() {

    }
}
