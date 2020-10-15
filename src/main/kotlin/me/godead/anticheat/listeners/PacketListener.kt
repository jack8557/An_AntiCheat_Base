package me.godead.anticheat.listeners

import io.github.retrooper.packetevents.event.PacketListener
import io.github.retrooper.packetevents.event.annotation.PacketHandler
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent
import io.github.retrooper.packetevents.event.impl.PacketSendEvent
import io.github.retrooper.packetevents.packettype.PacketType
import io.github.retrooper.packetevents.packetwrappers.`in`.entityaction.WrappedPacketInEntityAction
import io.github.retrooper.packetevents.packetwrappers.`in`.flying.WrappedPacketInFlying
import io.github.retrooper.packetevents.packetwrappers.`in`.useentity.WrappedPacketInUseEntity
import io.github.retrooper.packetevents.packetwrappers.out.animation.WrappedPacketOutAnimation
import me.godead.anticheat.extensions.getUser
import me.godead.anticheat.extensions.isFlying
import org.bukkit.Location

class PacketListener : PacketListener {

    @PacketHandler fun receiveEvent(event: PacketReceiveEvent) = event.player.getUser()?.inbound(event)

    @PacketHandler fun sendEvent(event: PacketSendEvent) = event.player.getUser()?.outbound(event)

    @PacketHandler
    fun handle(event: PacketReceiveEvent) {
        val user = event.player.getUser() ?: return
        user.cancelManager.handle(event)
        when {
            event.packetId.isFlying() -> {
                val flyPacket = WrappedPacketInFlying(event.nmsPacket)
                user.positionManager.handle(Location(user.player.world, flyPacket.x, flyPacket.y, flyPacket.z))
                user.positionManager.onGround(flyPacket.isOnGround)
            }
            event.packetId == PacketType.Client.USE_ENTITY -> {
                val useEntityPacket = WrappedPacketInUseEntity(event.nmsPacket)
                user.actionManager.onAttack(useEntityPacket.entity)
            }
            event.packetId == PacketType.Client.ENTITY_ACTION -> {
                val entityActionPacket = WrappedPacketInEntityAction(event.nmsPacket)
                user.actionManager.handle(entityActionPacket.action)
            }
        }
    }

    @PacketHandler
    fun handle(event: PacketSendEvent) {
        val user = event.player.getUser() ?: return
        when (event.packetId) {
            PacketType.Server.ENTITY_TELEPORT -> user.actionManager.onTeleport()
            PacketType.Server.ANIMATION -> {
                val animationPacket = WrappedPacketOutAnimation(event.nmsPacket)
                if (animationPacket.animationType == WrappedPacketOutAnimation.EntityAnimationType.TAKE_DAMAGE) user.actionManager.onDamage()
            }
        }
    }
}