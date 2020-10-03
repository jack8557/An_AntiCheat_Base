package me.godead.anticheat.users.impl

import io.github.retrooper.packetevents.packetwrappers.`in`.entityaction.WrappedPacketInEntityAction
import me.godead.anticheat.ticks.Ticks
import me.godead.anticheat.users.User
import org.bukkit.entity.Entity

class ActionManager(val user: User) {

    var sprinting = false
        private set
    var sneaking = false
        private set

    val damageTicks = Ticks(-99)

    val teleportTicks = Ticks(-99)

    var target: Entity = user.player
        private set

    fun onAttack(entity: Entity) = run { target = entity }

    fun onDamage() = damageTicks.setTicks(System.currentTimeMillis())

    fun onTeleport() = teleportTicks.setTicks(System.currentTimeMillis())

    fun handle(action: WrappedPacketInEntityAction.PlayerAction) {
        when (action) {
            WrappedPacketInEntityAction.PlayerAction.START_SNEAKING -> sneaking = true
            WrappedPacketInEntityAction.PlayerAction.STOP_SNEAKING -> sneaking = false
            WrappedPacketInEntityAction.PlayerAction.START_SPRINTING -> sprinting = true
            WrappedPacketInEntityAction.PlayerAction.STOP_SPRINTING -> sprinting = false
            else -> return
        }
    }


}