package me.godead.anticheat.check

import me.godead.anticheat.config.AntiCheatConfig
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList


object CheckManager {

    val checks = ArrayList<Class<*>>()

    fun loadChecks(): List<Check> {
        val checklist: MutableList<Check> = ArrayList()
        checks
            .forEach(Consumer { check: Class<*> ->
                try {
                    checklist.add(check.getConstructor().newInstance() as Check)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })

        for (check in checklist) {
            Arrays.stream(check.javaClass.declaredFields).filter { field ->
                field.isAccessible = true
                field.isAnnotationPresent(ConfigValue::class.java)
            }.forEach { field ->
                try {
                    field.isAccessible = true
                    val path = check.checkConfigName + ".settings." + field.name
                    val config =
                        AntiCheatConfig.getCheckForName(field.getAnnotation(ConfigValue::class.java).configName)
                    if (config.customConfig.get(path) != null) {
                        val `val`: Any = config.customConfig.get(path)!!
                        if (`val` is Double && field[check] is Float) {
                            field[check] = `val`.toFloat()
                        } else {
                            field[check] = `val`
                        }
                    } else {
                        config.getOrSet(field.getAnnotation(ConfigValue::class.java).path, field[check])
                        config.save()
                    }
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }
        return checklist
    }
}