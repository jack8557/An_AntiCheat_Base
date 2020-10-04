package me.godead.anticheat.users.impl

import me.godead.anticheat.users.User
import me.godead.anticheat.utils.XMaterial

class CollisionManager(val user: User) {

     fun touchingAny(material: XMaterial) = user.positionManager.boundingBox.checkBlocksAny(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }


     fun touchingAll(material: XMaterial) = user.positionManager.boundingBox.checkBlocks(
        user.player.world
    ) { XMaterial: XMaterial -> XMaterial == material }


    //fun touchingAny(vararg materials: XMaterial) = materials.any { touchingAny(it) }

    //fun touchingAll(vararg materials: XMaterial) = materials.any { touchingAll(it) }

}