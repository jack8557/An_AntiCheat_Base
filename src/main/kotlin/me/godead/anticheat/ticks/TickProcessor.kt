package me.godead.anticheat.ticks

import me.godead.anticheat.extensions.getAABB
import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.users.UserManager
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

class TickProcessor : Runnable {

    var ticks = 0
        private set

    fun start() = run { task = Bukkit.getScheduler().runTaskTimer(AntiCheatManager.plugin, this, 0L, 1L) }


    fun stop() = task.cancel()


    override fun run() {
        ticks++
        UserManager.users.parallelStream().forEach {
            val target = it.actionManager.target
            val aabb = target.getAABB()
            if (aabb != null) it.targetLocations.add(Pair(aabb, ticks))
        }
    }

    companion object {
        private lateinit var task: BukkitTask
    }
}

