package me.godead.anticheat.lagback

import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.users.User
import org.bukkit.Bukkit

object LagBack {

    fun lagBack(user: User) = Bukkit.getScheduler()
        .runTask(AntiCheatManager.plugin, Runnable { user.player.teleport(user.lastLegitLocation) })

}