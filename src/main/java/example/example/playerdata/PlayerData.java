package example.example.playerdata;

import me.godead.anticheat.users.User;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlayerData extends User {

    public PlayerData(@NotNull UUID uuid) {
        super(uuid);
    }

    private int test = 5;

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
