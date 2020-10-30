package me.godead.anticheat.users.impl

import me.godead.anticheat.users.BoundingBox
import me.godead.anticheat.users.User
import me.godead.anticheat.utils.XMaterial

class CollisionManager(val user: User) {

    val isAgainstWall
        get() =
            !user.collisionManager.touchingAll(
                BoundingBox(
                    user.player.location.clone()
                        .add(0.0, 0.5, 0.0)
                )
                    .expand(0.5, 0.0, 0.5), XMaterial.AIR
            ) ||
                    !user.collisionManager.touchingAll(
                        BoundingBox(
                            user.player.location.clone()
                                .add(0.0, 1.5, 0.0)
                        ).expand(0.5, 0.0, 0.5), XMaterial.AIR
                    )

    val isUnderBlock
        get() = !user.collisionManager.touchingAll(
            BoundingBox(
                user.player.location.clone()
                    .add(0.0, 2.2, 0.0)
            ).expand(0.25, 0.0, 0.25), XMaterial.AIR
        )

    fun touchingAny(material: XMaterial) = user.positionManager.boundingBox.checkBlocksAny(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }

    fun touchingAll(material: XMaterial) = user.positionManager.boundingBox.checkBlocks(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }


    fun touchingAny(vararg materials: XMaterial) = materials.any { touchingAny(it) }

    fun touchingAll(vararg materials: XMaterial) = materials.any { touchingAll(it) }


    fun touchingAny(boundingBox: BoundingBox, material: XMaterial) = boundingBox.checkBlocksAny(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }


    fun touchingAll(boundingBox: BoundingBox, material: XMaterial) = boundingBox.checkBlocks(
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

    fun isNear(material: String): Boolean {
        val location = user.player.location
        val expand = 0.3
        var x = -expand
        while (x <= expand) {
            var z = -expand
            while (z <= expand) {
                if (location.clone().add(x, 0.1, z).block.toString().toLowerCase()
                        .contains(material.toLowerCase()) || location.clone().add(x, -0.5001, z).block.toString()
                        .toLowerCase().contains(material.toLowerCase())
                ) {
                    return true
                }
                z += expand
            }
            x += expand
        }
        return false
    }

}