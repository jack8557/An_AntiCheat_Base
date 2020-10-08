package me.godead.anticheat.alert

import me.godead.anticheat.check.Check
import me.godead.anticheat.extensions.color
import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.users.User
import me.godead.anticheat.users.UserManager
import org.bukkit.Bukkit

open class AlertManager {

    open fun onFlag(user: User, check: Check) {
        UserManager.users.parallelStream()
            .filter { it.alerts }
            .filter { it.player.hasPermission("anticheat.alerts") }
            .forEach { it.player.sendMessage("&8[&c&l!&8] &c${user.player.name} &7failed &6${check.checkName} ${check.checkType} &ex${check.vl}".color()) }
        if (check.vl >= check.maxVL) onPunish(user, check)
    }

    open fun onPunish(user: User, check: Check) {
        if (!check.punishable) return
        Bukkit.getScheduler().runTask(AntiCheatManager.plugin, Runnable { user.player.kickPlayer("&7You have been kicked for &c${check.checkName}".color()) })
    }

}



