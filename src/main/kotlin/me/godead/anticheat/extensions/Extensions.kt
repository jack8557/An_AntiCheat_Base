@file:JvmName(name = "Extensions")

package me.godead.anticheat.extensions

import io.github.retrooper.packetevents.PacketEvents
import io.github.retrooper.packetevents.event.PacketListener
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent
import io.github.retrooper.packetevents.packettype.PacketType
import io.github.retrooper.packetevents.packetwrappers.`in`.useentity.WrappedPacketInUseEntity
import io.github.retrooper.packetevents.utils.server.ServerVersion
import me.godead.anticheat.check.Check
import me.godead.anticheat.check.CheckManager
import me.godead.anticheat.plugin.AntiCheatManager
import me.godead.anticheat.plugin.AntiCheatPlugin
import me.godead.anticheat.users.User
import me.godead.anticheat.users.UserManager
import me.godead.anticheat.utils.ReflectionsUtil
import me.godead.anticheat.utils.XMaterial
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

fun Player.getUser() = UserManager.getUser(uniqueId)

fun Player.register() {
    UserManager.register(AntiCheatManager.customUser?.constructors?.first()?.newInstance(uniqueId) as User)
    getUser()?.joinTicks = AntiCheatManager.tickProcessor.ticks
}

fun Player.unregister() = getUser()?.let { UserManager.unregister(it) }

/**
 * Registers a Bukkit Event for you
 * */
fun registerEvent(vararg event: Listener) =
    event.forEach { AntiCheatManager.plugin.server.pluginManager.registerEvents(it, AntiCheatManager.plugin) }

/**
 * Registers a Packet Event for you
 * */
fun registerEvent(vararg event: PacketListener) =
    event.forEach { PacketEvents.getAPI().eventManager.registerListener(it) }

/**
 * Registers a Check for you
 * */
fun registerCheck(vararg check: Check) = check.forEach { CheckManager.checks.add(it.javaClass) }

fun Any.debug() = Bukkit.broadcastMessage(toString().color())

fun Block.xType(): XMaterial {
    val valueBefore: String = this.toString().substringAfter("type=")
    val valuePre: String = valueBefore.substringBefore(",data")
    val value: String = valuePre.toUpperCase().replace(' ', '_')

    return try {
        XMaterial.valueOf(value)
    } catch (ex: java.lang.IllegalArgumentException) {
        XMaterial.requestOldXMaterial(value, -1)!!
    }
}

fun XMaterial.isSlab() = this.toString().toLowerCase().contains("slab")


fun XMaterial.isStair() = this.toString().toLowerCase().contains("stair")


fun XMaterial.isBouncable() = this == XMaterial.SLIME_BLOCK || this == XMaterial.HONEY_BLOCK


fun Byte.isFlying() = PacketType.Client.Util.isInstanceOfFlying(this)

fun PacketReceiveEvent.isAttack() =
    if (this.packetId != PacketType.Client.USE_ENTITY) false
    else WrappedPacketInUseEntity(this.nmsPacket).action == WrappedPacketInUseEntity.EntityUseAction.ATTACK


fun User.isGliding() =
    if (PacketEvents.getAPI().serverUtils.version.isHigherThan(ServerVersion.v_1_8_8)) this.player.isGliding else false

fun Player.hasLevitation() =
    if (PacketEvents.getAPI().serverUtils.version.isLowerThan(ServerVersion.v_1_9)) false else this.hasPotionEffect(
        PotionEffectType.LEVITATION
    )


fun Player.getPotionLevel(effect: PotionEffectType) =
    if (!this.hasPotionEffect(effect)) 0 else this.activePotionEffects.stream()
        .filter { potionEffect: PotionEffect -> potionEffect.type.id == effect.id }
        .map { obj: PotionEffect -> obj.amplifier }.findAny().orElse(0) + 1


fun String.color() = ChatColor.translateAlternateColorCodes('&', this)


fun Entity.getAABB() = BoundingBoxUtils.getEntityBoundingBox(this as LivingEntity)

/**
 * Returns 1 if the condition is true
 * or 0 if the condition is false
 * */
fun Boolean.toInt() = if (this) 1 else 0


object BoundingBoxUtils {
    fun getEntityBoundingBox(entity: LivingEntity) =
        ReflectionsUtil.getBoundingBox(entity)?.let { ReflectionsUtil.toBoundingBox(it) }

}
