package me.godead.anticheat.users

import java.util.*

object UserManager {

    val users: MutableList<User> = ArrayList()

    fun register(user: User) = users.add(user)

    fun unregister(user: User) = users.remove(user)

    fun getUser(uuid: UUID): User? =  users.firstOrNull { it.player.uniqueId == uuid }
}