package me.godead.anticheat.users.impl

import me.godead.anticheat.users.BoundingBox
import me.godead.anticheat.users.User
import me.godead.anticheat.utils.XMaterial

class CollisionManager(val user: User) {

    private fun touchingAny(material: XMaterial) = user.positionManager.boundingBox.checkBlocksAny(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }


    private fun touchingAll(material: XMaterial) = user.positionManager.boundingBox.checkBlocks(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }


    fun touchingAny(vararg materials: XMaterial) = materials.any { touchingAny(it) }

    fun touchingAll(vararg materials: XMaterial) = materials.any { touchingAll(it) }


    private fun touchingAny(boundingBox: BoundingBox, material: XMaterial) = boundingBox.checkBlocksAny(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }


    private fun touchingAll(boundingBox: BoundingBox, material: XMaterial) = boundingBox.checkBlocks(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }


    fun touchingAny(boundingBox: BoundingBox, vararg materials: XMaterial) =
        materials.any { touchingAny(boundingBox, it) }

    fun touchingAll(boundingBox: BoundingBox, vararg materials: XMaterial) =
        materials.any { touchingAll(boundingBox, it) }

    fun touchingAny(height: Double, vararg materials: XMaterial) = materials.any {
        touchingAny(
            BoundingBox(
                user.player.location.clone().add(0.0, height, 0.0)
            ), it
        )
    }

    fun touchingAll(height: Double, vararg materials: XMaterial) = materials.any {
        touchingAll(
            BoundingBox(
                user.player.location.clone().add(0.0, height, 0.0)
            ), it
        )
    }

}