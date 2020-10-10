package me.godead.anticheat.plugin

import io.github.retrooper.packetevents.PacketEvents
import me.godead.anticheat.alert.AlertManager
import me.godead.anticheat.config.CheckConfig
import me.godead.anticheat.config.SettingsConfig
import me.godead.anticheat.extensions.registerEvent
import me.godead.anticheat.listeners.BukkitListener
import me.godead.anticheat.listeners.PacketListener
import me.godead.anticheat.ticks.TickProcessor
import me.godead.anticheat.users.User
import org.bukkit.plugin.Plugin


internal object AntiCheatManager {

    lateinit var plugin: Plugin

    val tickProcessor: TickProcessor = TickProcessor()

    lateinit var defaultCheckConfig: CheckConfig

    lateinit var settingsConfig: SettingsConfig

    fun init(plugin: Plugin) {
        this.plugin = plugin
        PacketEvents.load()
        PacketEvents.getSettings().identifier = plugin.javaClass.name
        PacketEvents.init(plugin)

        settingsConfig = SettingsConfig()
        settingsConfig.createConfig()

        defaultCheckConfig = CheckConfig()
        defaultCheckConfig.createConfig()

        registerEvent(BukkitListener())
        registerEvent(PacketListener())

        tickProcessor.start()
    }

    fun stop(plugin: Plugin) {
        this.plugin = plugin
        PacketEvents.stop()
        tickProcessor.stop()
    }

    var customUser: Class<*>? = null
        get() = if (field == null) User::class.java else field


    var alertManager: AlertManager? = null
        get() = if (field == null) AlertManager() else field
}