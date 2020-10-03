package example.example.checks;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketSendEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import me.godead.anticheat.check.Check;
import me.godead.anticheat.check.Info;
import me.godead.anticheat.users.User;
import example.example.ExampleMain;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

@Info(name = "Flight", type = 'A')
public class ExampleCheck extends Check {

    //@ConfigValue(configName = "ExampleConfig", name = "ExampleValue", path = "Example.plugin")
    int exampleValue = 5;

    @Override
    public void onPacketReceive(@NotNull PacketReceiveEvent event, @NotNull User user) {
        if (event.getPacketId() == PacketType.Client.FLYING) {
            if (user.getPositionManager().getDeltaY() < 1e-11) {
                Bukkit.getScheduler().runTask(ExampleMain.plugin, () -> user.getPlayer().kickPlayer("Hacker"));
            }
        }
    }

    @Override
    public void onPacketSend(@NotNull PacketSendEvent event, @NotNull User user) {
        super.onPacketSend(event, user);
    }
}
