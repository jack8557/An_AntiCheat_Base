package me.godead.anticheat.listeners

import me.godead.anticheat.extensions.register
import me.godead.anticheat.extensions.unregister
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class BukkitListener : Listener {

    @EventHandler fun joinEvent(event: PlayerJoinEvent) = event.player.register()

    @EventHandler fun quitEvent(event: PlayerQuitEvent) = event.player.unregister()

}