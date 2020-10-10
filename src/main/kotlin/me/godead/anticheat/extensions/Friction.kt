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

fun Block.isSolid() = when {
    xType() == XMaterial.GRASS -> false
    xType() == XMaterial.TALL_GRASS -> false
    xType() == XMaterial.SEAGRASS -> false
    xType() == XMaterial.TALL_SEAGRASS -> false
    xType() == XMaterial.CORNFLOWER -> false
    xType() == XMaterial.SUNFLOWER -> false
    xType() == XMaterial.DEAD_BUSH -> false
    xType() == XMaterial.CHORUS_FLOWER -> false
    xType() == XMaterial.DANDELION -> false
    xType() == XMaterial.POPPY -> false
    xType() == XMaterial.BLUE_ORCHID -> false
    xType() == XMaterial.ALLIUM -> false
    xType() == XMaterial.AZURE_BLUET -> false
    xType() == XMaterial.OXEYE_DAISY -> false
    xType() == XMaterial.LILY_OF_THE_VALLEY -> false
    xType() == XMaterial.WITHER_ROSE -> false
    xType() == XMaterial.ROSE_BUSH -> false
    xType() == XMaterial.SWEET_BERRY_BUSH -> false
    xType() == XMaterial.LILAC -> false
    xType() == XMaterial.PEONY -> false
    xType() == XMaterial.WHEAT -> false
    xType() == XMaterial.CARROTS -> false
    xType() == XMaterial.BEETROOTS -> false
    xType() == XMaterial.POTATOES -> false
    xType() == XMaterial.SUGAR_CANE -> false
    xType() == XMaterial.BAMBOO -> false
    xType() == XMaterial.KELP_PLANT -> false
    xType() == XMaterial.RED_MUSHROOM_BLOCK -> false
    xType() == XMaterial.BROWN_MUSHROOM_BLOCK -> false
    xType() == XMaterial.NETHER_WART_BLOCK -> false
    xType() == XMaterial.FERN -> false
    xType() == XMaterial.CRIMSON_FUNGUS -> false
    xType() == XMaterial.WARPED_FUNGUS -> false
    xType() == XMaterial.NETHER_SPROUTS -> false
    xType() == XMaterial.CRIMSON_ROOTS -> false
    xType() == XMaterial.WARPED_ROOTS -> false
    xType() == XMaterial.SEAGRASS -> false
    xType() == XMaterial.TALL_SEAGRASS -> false
    xType().name.contains("TORCH", true) -> false
    xType().name.contains("STEM", true) -> false
    xType().name.contains("VINE", true) -> false
    xType().name.contains("SIGN", true) -> false
    xType().name.contains("SAPLING", true) -> false
    xType().name.contains("TULIP", true) -> false
    (xType().name.contains("CORAL", true) && !xType().name.contains("BLOCK", true)) -> false
    xType().name.contains("BANNER", true) -> false
    else -> true
}
