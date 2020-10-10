@file:JvmName("Friction")

package me.godead.anticheat.extensions

import me.godead.anticheat.utils.XMaterial
import org.bukkit.block.Block

fun Block.getFriction() = when (xType()) {
    XMaterial.ICE -> 0.98
    XMaterial.PACKED_ICE -> 0.98
    XMaterial.BLUE_ICE -> 0.989
    XMaterial.SLIME_BLOCK -> 0.8
    else -> 0.6
}
