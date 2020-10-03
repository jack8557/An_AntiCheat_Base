package me.godead.anticheat.check

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE)
annotation class ConfigValue(val name: String = "", val configName: String = "", val path: String = "")