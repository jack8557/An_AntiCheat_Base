package me.godead.anticheat.check

import me.godead.anticheat.config.AntiCheatConfig
import me.godead.anticheat.plugin.AntiCheatManager
import java.util.*
import java.util.function.Consumer
import kotlin.NoSuchElementException
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
                    /*val config = try {
                        AntiCheatConfig.getCheckForName(field.getAnnotation(ConfigValue::class.java).configName)
                    } catch (ex: NoSuchElementException) {
                        null
                    }*/
                    if (AntiCheatManager.defaultCheckConfig.customConfig.get(path) != null) {
                        val `val`: Any = AntiCheatManager.defaultCheckConfig.customConfig.get(path)!!
                        if (`val` is Double && field[check] is Float) {
                            field[check] = `val`.toFloat()
                        } else {
                            field[check] = `val`
                        }
                    } else {
                        AntiCheatManager.defaultCheckConfig.getOrSet(
                            try {
                                field.getAnnotation(ConfigValue::class.java).path
                            } catch (ex: Exception) {
                                path
                            }, field[check]
                        )
                        AntiCheatManager.defaultCheckConfig.save()
                    }
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }
        return checklist
    }
}