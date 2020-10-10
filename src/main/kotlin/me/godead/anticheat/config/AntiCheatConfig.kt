package me.godead.anticheat.config

import me.godead.anticheat.plugin.AntiCheatManager
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

open class AntiCheatConfig(val configName: String) {

    companion object {
        val configs = ArrayList<AntiCheatConfig>()
        fun getCheckForName(name: String) =
            configs.stream().filter { config -> config.configName == name }.findFirst().get()
    }

    private lateinit var customConfigFile: File

    lateinit var customConfig: FileConfiguration

    fun save() = customConfig.save(customConfigFile)


    fun createConfig(): AntiCheatConfig {
        customConfigFile = File(AntiCheatManager.plugin.dataFolder, "$configName.yml")
        if (!customConfigFile.exists()) {
            customConfigFile.parentFile.mkdirs()
            customConfigFile.createNewFile()
            //AntiCheatManager.plugin.saveResource("$configName.yml", false)
        }
        customConfig = YamlConfiguration()
        try {
            (customConfig as YamlConfiguration).load(customConfigFile)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
        return this
    }

    fun getOrSet(path: String, value: Any) =
        if (!customConfig.isSet(path)) {
            customConfig.set(path, value)
            save()
            value
        } else customConfig.get(path)!!


    init {
        configs.add(this)
    }

}