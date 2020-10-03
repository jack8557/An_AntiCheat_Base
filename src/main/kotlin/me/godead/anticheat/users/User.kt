package me.godead.anticheat.users

import io.github.retrooper.packetevents.PacketEvents
import io.github.retrooper.packetevents.enums.ClientVersion
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent
import io.github.retrooper.packetevents.event.impl.PacketSendEvent
import me.godead.anticheat.check.Check
import me.godead.anticheat.check.CheckManager
import me.godead.anticheat.users.impl.ActionManager
import me.godead.anticheat.users.impl.CollisionManager
import me.godead.anticheat.users.impl.PositionManager
import me.godead.anticheat.utils.EvictingList
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

open class User(uuid: UUID) {

    val player: Player = Bukkit.getPlayer(uuid)!!

    private val checks: List<Check> = CheckManager.loadChecks()

    var alerts: Boolean = true

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    var targetLocations: EvictingList<Pair<BoundingBox, Int>> = EvictingList(30)

    val clientVersion: ClientVersion = PacketEvents.getAPI().playerUtils.getClientVersion(player)

    val ping get() = PacketEvents.getAPI().playerUtils.getPing(player)

    val actionManager = ActionManager(this)

    val positionManager = PositionManager(this)

    val collisionManager = CollisionManager(this)

    fun inbound(event: PacketReceiveEvent) {
        executorService.execute {
            checks.stream().forEach { check: Check ->
                UserManager.getUser(event.player.uniqueId)?.let {
                    check.onPacketReceive(
                        event,
                        it
                    )
                }
            }
        }
    }

    fun outbound(event: PacketSendEvent) {
        executorService.execute {
            checks.stream().forEach { check: Check ->
                UserManager.getUser(event.player.uniqueId)?.let {
                    check.onPacketSend(
                        event,
                        it
                    )
                }
            }
        }
    }
}