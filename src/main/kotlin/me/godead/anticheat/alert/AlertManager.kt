package me.godead.anticheat.alert

import me.godead.anticheat.check.Check
import me.godead.anticheat.extensions.color
import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.users.User
import me.godead.anticheat.users.UserManager
import org.bukkit.Bukkit

open class AlertManager {

    private val alertMessage: String = AntiCheatManager.defaultConfig.getOrSet("Alerts.Message","&8[&c&l!&8] &c%player% &7failed &6%check_name% %check_type% &ex%vl%") as String
    private val alertPermission: String = AntiCheatManager.defaultConfig.getOrSet("Alerts.Permission","anticheat.alerts") as String

    private val punishCommands: List<String> = AntiCheatManager.defaultConfig.getOrSet("Punish.Commands", listOf("kick %player% &7You have been kicked for &c%check_name%")) as List<String>

    open fun onFlag(user: User, check: Check) {
        alertMessage.replace("%player%", user.player.name)
            .replace("%check_name%", check.checkName)
            .replace("%check_type%", check.checkType.toString())
            .replace("%vl%", check.vl.toString())

        UserManager.users.parallelStream()
            .filter { it.alerts }
            .filter { it.player.hasPermission(alertPermission) }
            .forEach { it.player.sendMessage(alertMessage.color()) }
        if (check.vl >= check.maxVL) onPunish(user, check)
    }

    open fun onPunish(user: User, check: Check) {
        if (!check.punishable) return
        Bukkit.getScheduler().runTask(AntiCheatManager.plugin, Runnable {
            punishCommands.forEach {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                it
                .replace("%player%", user.player.name)
                .replace("%check_name%", check.checkName)
                .replace("%check_type%", check.checkType.toString())
                .replace("%vl%", check.vl.toString())
                .color())
            }
        })
    }

}



