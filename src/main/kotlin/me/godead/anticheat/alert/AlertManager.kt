package me.godead.anticheat.alert

import me.godead.anticheat.check.Check
import me.godead.anticheat.extensions.color
import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.users.User
import me.godead.anticheat.users.UserManager
import org.bukkit.Bukkit

open class AlertManager {

    private val alertMessage: String = AntiCheatManager.
    settingsConfig.getOrSet(
        "Alerts.Message",
        "&8[&c&l!&8] &c%player% &7failed &6%check_name% %check_type% &ex%vl%"
    ) as String

    private val alertPermission: String =
        AntiCheatManager.settingsConfig.getOrSet("Alerts.Permission", "anticheat.alerts") as String

    private val punishCommands: List<String> = AntiCheatManager.settingsConfig.getOrSet(
        "Punish.Commands",
        listOf("kick %player% &7You have been kicked for &c%check_name%")
    ) as List<String>

    private val punishBroadcastEnabled: Boolean = AntiCheatManager.settingsConfig.getOrSet(
        "Punish.Broadcast.Enabled",
        true
    ) as Boolean

    private val punishBroadcastMessage: String = AntiCheatManager.settingsConfig.getOrSet(
        "Punish.Broadcast.Message",
        "&8[&c&l!&8] &c%player% &7has been removed from the server for cheating."
    ) as String

    open fun onFlag(user: User, check: Check) {
        UserManager.users.parallelStream()
            .filter { it.alerts }
            .filter { it.player.hasPermission(alertPermission) }
            .forEach { it.player.sendMessage(alertMessage.applyPlaceHolders(user, check)) }
        if (check.vl >= check.maxVL) onPunish(user, check)
    }

    open fun onPunish(user: User, check: Check) {
        if (!check.punishable) return
        Bukkit.getScheduler().runTask(AntiCheatManager.plugin, Runnable {
            punishCommands.forEach {
                Bukkit.dispatchCommand(
                    Bukkit.getConsoleSender(),
                    it.applyPlaceHolders(user, check)
                )
            }
        })
        if (punishBroadcastEnabled) Bukkit.broadcastMessage(punishBroadcastMessage.applyPlaceHolders(user, check))
    }

    private fun String.applyPlaceHolders(user: User, check: Check) =
        replace("%player%", user.player.name)
            .replace("%check_name%", check.checkName)
            .replace("%check_type%", check.checkType.toString())
            .replace("%vl%", check.vl.toString())
            .color()


}



