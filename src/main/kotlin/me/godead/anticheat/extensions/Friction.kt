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

/*
fun Block.isSolid() = xType().isSolid()

fun XMaterial.isSolid() = when {
    this == XMaterial.GRASS -> false
    this == XMaterial.TALL_GRASS -> false
    this == XMaterial.SEAGRASS -> false
    this == XMaterial.TALL_SEAGRASS -> false
    this == XMaterial.CORNFLOWER -> false
    this == XMaterial.SUNFLOWER -> false
    this == XMaterial.DEAD_BUSH -> false
    this == XMaterial.CHORUS_FLOWER -> false
    this == XMaterial.DANDELION -> false
    this == XMaterial.POPPY -> false
    this == XMaterial.BLUE_ORCHID -> false
    this == XMaterial.ALLIUM -> false
    this == XMaterial.AZURE_BLUET -> false
    this == XMaterial.OXEYE_DAISY -> false
    this == XMaterial.LILY_OF_THE_VALLEY -> false
    this == XMaterial.WITHER_ROSE -> false
    this == XMaterial.ROSE_BUSH -> false
    this == XMaterial.SWEET_BERRY_BUSH -> false
    this == XMaterial.LILAC -> false
    this == XMaterial.PEONY -> false
    this == XMaterial.WHEAT -> false
    this == XMaterial.CARROTS -> false
    this == XMaterial.BEETROOTS -> false
    this == XMaterial.POTATOES -> false
    this == XMaterial.SUGAR_CANE -> false
    this == XMaterial.BAMBOO -> false
    this == XMaterial.KELP_PLANT -> false
    this == XMaterial.RED_MUSHROOM_BLOCK -> false
    this == XMaterial.BROWN_MUSHROOM_BLOCK -> false
    this == XMaterial.NETHER_WART_BLOCK -> false
    this == XMaterial.FERN -> false
    this == XMaterial.CRIMSON_FUNGUS -> false
    this == XMaterial.WARPED_FUNGUS -> false
    this == XMaterial.NETHER_SPROUTS -> false
    this == XMaterial.CRIMSON_ROOTS -> false
    this == XMaterial.WARPED_ROOTS -> false
    this == XMaterial.SEAGRASS -> false
    this == XMaterial.TALL_SEAGRASS -> false
    this.name.contains("TORCH", true) -> false
    this.name.contains("STEM", true) -> false
    this.name.contains("VINE", true) -> false
    this.name.contains("SIGN", true) -> false
    this.name.contains("SAPLING", true) -> false
    this.name.contains("TULIP", true) -> false
    (this.name.contains("CORAL", true) && !this.name.contains("BLOCK", true)) -> false
    this.name.contains("BANNER", true) -> false
    else -> true
}
*/
