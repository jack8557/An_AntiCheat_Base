package me.godead.anticheat.plugin

import io.github.retrooper.packetevents.PacketEvents
import me.godead.anticheat.extensions.registerEvent
import me.godead.anticheat.listeners.BukkitListener
import me.godead.anticheat.listeners.PacketListener
import me.godead.anticheat.ticks.TickProcessor
import org.bukkit.plugin.Plugin


internal object AntiCheatManager {

    lateinit var plugin: Plugin

    private val tickProcessor: TickProcessor = TickProcessor()

    fun init(plugin: Plugin) {
        this.plugin = plugin
        PacketEvents.load()
        PacketEvents.getSettings().identifier = plugin.javaClass.name
        PacketEvents.init(plugin)
        registerEvent(BukkitListener())
        tickProcessor.start()
        registerEvent(PacketListener())
    }

    fun stop(plugin: Plugin) {
        this.plugin = plugin
        PacketEvents.stop()
        tickProcessor.stop()
    }

    var customUser: Class<*>? = null
}