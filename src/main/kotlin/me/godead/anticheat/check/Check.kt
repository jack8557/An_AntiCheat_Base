package me.godead.anticheat.check

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent
import io.github.retrooper.packetevents.event.impl.PacketSendEvent
import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.users.User

open class Check {

    var vl = 0
    var maxVL = 10

    var enabled = false
    var punishable = false

    var preVL = 0.0

    var checkName: String = this.javaClass.getAnnotation(Info::class.java).name

    var checkType: Char = this.javaClass.getAnnotation(Info::class.java).type

    var checkConfigName: String = checkName + checkType

    open fun onPacketReceive(event: PacketReceiveEvent, user: User) {}

    open fun onPacketSend(event: PacketSendEvent, user: User) {}

    protected fun flag(user: User) {
        if (!enabled) return
        vl++
        AntiCheatManager.alertManager!!.onFlag(user, this)
        user.actionManager.flagTicks.setTicks(System.currentTimeMillis())
    }

    init {
        enabled = AntiCheatManager.defaultCheckConfig.getOrSet("$checkConfigName.enabled", true) as Boolean
        punishable = AntiCheatManager.defaultCheckConfig.getOrSet("$checkConfigName.punishable", true) as Boolean
        maxVL = AntiCheatManager.defaultCheckConfig.getOrSet("$checkConfigName.max-vl", 10) as Int
    }

    fun decreasePreVL(amount: Double, coerceAtLeast: Double = 0.0) = preVL.minus(amount).coerceAtLeast(coerceAtLeast)

}