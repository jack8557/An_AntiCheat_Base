package me.godead.anticheat.plugin

import io.github.retrooper.packetevents.PacketEvents
import io.github.retrooper.packetevents.event.PacketListener
import javafx.scene.control.Alert
import me.godead.anticheat.alert.AlertManager
import me.godead.anticheat.check.Check
import me.godead.anticheat.check.CheckManager
import me.godead.anticheat.users.User
import me.godead.lilliputian.Dependency
import me.godead.lilliputian.Lilliputian
import me.godead.lilliputian.Repository
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

open class AntiCheatPlugin : JavaPlugin() {

    override fun onEnable() {
        val lilliputian = Lilliputian(this)
        lilliputian.dependencyBuilder
            .addDependency(
                Dependency(
                    Repository.JITPACK,
                    "com.github.retrooper",
                    "packetevents",
                    "1.7-PRE-2"
                )
            )
            .addDependency(
                Dependency(
                    Repository.MAVENCENTRAL,
                    "org.jetbrains.kotlin",
                    "kotlin-stdlib",
                    "1.4.10"
                )
            )
            .loadDependencies()
        onStart()
        AntiCheatManager.init(this)
        onStartFinish()
    }

    override fun onDisable() {
        onStop()
        AntiCheatManager.stop(this)
    }

    open fun onStart() {}

    open fun onStartFinish() {}

    open fun onStop() {}

    fun setUser(user: Class<*>) {
        AntiCheatManager.customUser = user
    }

    fun setAlertManager(alertManager: AlertManager) {
        AntiCheatManager.alertManager = alertManager
    }

    val plugin: Plugin
        get() = AntiCheatManager.plugin

    /**
     * Registers a Bukkit Event for you
     * */
    fun registerEvent(vararg event: Listener) =
        event.forEach { AntiCheatManager.plugin.server.pluginManager.registerEvents(it, AntiCheatManager.plugin) }

    /**
     * Registers a Packet Event for you
     * */
    fun registerEvent(vararg event: PacketListener) =
        event.forEach { PacketEvents.getAPI().eventManager.registerListener(it) }

    /**
     * Registers a Check for you
     * */
    fun registerCheck(vararg check: Check) = check.forEach { CheckManager.checks.add(it.javaClass) }
}