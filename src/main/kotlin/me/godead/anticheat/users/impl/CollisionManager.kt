package me.godead.anticheat.users.impl

import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.users.User
import me.godead.anticheat.utils.XMaterial
import org.bukkit.Bukkit

class CollisionManager(val user: User) {

    private fun touchingAny(material: XMaterial): Boolean {
        var touching = false
        Bukkit.getScheduler().runTask(AntiCheatManager.plugin,
            Runnable {
                touching = user.positionManager.boundingBox.checkBlocksAny(
                    user.player.world
                ) { XMaterial: XMaterial -> XMaterial == material }
            })
        return touching
    }

    private fun touchingAll(material: XMaterial): Boolean {
        var touching = false
        Bukkit.getScheduler().runTask(AntiCheatManager.plugin,
            Runnable {
                touching = user.positionManager.boundingBox.checkBlocks(
                    user.player.world
                ) { XMaterial: XMaterial -> XMaterial == material }
            })
        return touching
    }


    fun touchingAny(vararg materials: XMaterial) = materials.any { touchingAny(it) }

    fun touchingAll(vararg materials: XMaterial) = materials.any { touchingAll(it) }

}