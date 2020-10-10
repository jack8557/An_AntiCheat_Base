@file:JvmName("Friction")

package me.godead.anticheat.extensions

import org.bukkit.block.Block

fun Block.getFriction() = when (xType()) {

    else -> 0.6
}
