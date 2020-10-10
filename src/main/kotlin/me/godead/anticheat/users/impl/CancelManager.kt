package me.godead.anticheat.users.impl

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent
import me.godead.anticheat.users.User

class CancelManager(val user: User) {

    private val toCancel = mutableSetOf<Byte>()

    fun cancelNext(packetType: Byte) = toCancel.add(packetType)

    fun cancelNext(vararg packetType: Byte) = packetType.forEach { cancelNext(it) }

    fun handle(packet: PacketReceiveEvent) {
        if (toCancel.contains(packet.packetId)) {
            toCancel.remove(packet.packetId)
            packet.cancel()
        }
    }
}