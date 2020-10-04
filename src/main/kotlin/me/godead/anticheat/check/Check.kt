package me.godead.anticheat.check

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent
import io.github.retrooper.packetevents.event.impl.PacketSendEvent
import me.godead.anticheat.alert.AlertManager
import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.users.User

open class Check {

    var vl = 0
    var maxVL = 15
    var enabled = false
    var punishable = false

    var preVL = 0

    var checkName: String = this.javaClass.getAnnotation(Info::class.java).name

    var checkType: Char = this.javaClass.getAnnotation(Info::class.java).type

    var checkConfigName: String = checkName + checkType

    open fun onPacketReceive(event: PacketReceiveEvent, user: User) {}

    open fun onPacketSend(event: PacketSendEvent, user: User) {}

    protected fun flag(user: User) {
        vl++
        if (AntiCheatManager.alertManager != null) {
            AntiCheatManager.alertManager!!.onFlag(user, this)
        } else {
            AlertManager().onFlag(user, this)
        }
    }

    init {
        //enabled = CheckConfig.getCheckConfig().setIfAbsent("$checkConfigName.enabled", true)
        //punishable = CheckConfig.getCheckConfig().setIfAbsent("$checkConfigName.punishable", true)
        //maxVL = CheckConfig.getCheckConfig().setIfAbsent("$checkConfigName.max-vl", 15)
    }
}