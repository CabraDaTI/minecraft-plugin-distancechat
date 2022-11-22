package org.cabradati.distancechat

import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.distancechat.events.PlayerChatEvent

class App: JavaPlugin() {

    companion object {
        const val ATIVAR_PLUGIN = "plugin.enabled"
        const val ATIVAR_DEBUG = "plugin.debug"
        const val DISTANCIA = "config.distance"
        const val DISTANCIA_MINIMA = "config.min_distance"
        const val DISTANCIA_MAXIMA = "config.max_distance"
    }

    override fun onEnable() {

        config.addDefault(ATIVAR_PLUGIN, true)
        config.addDefault(ATIVAR_DEBUG, false)
        config.addDefault(DISTANCIA, 28)
        config.addDefault(DISTANCIA_MINIMA, 10)
        config.addDefault(DISTANCIA_MAXIMA, 60)
        config.options().copyDefaults(true)
        saveConfig()

        val diContainer = DIContainer(
            this,
            server,
            config
        )

        if(config.getBoolean(ATIVAR_PLUGIN)) {
            server.pluginManager.registerEvents(
                PlayerChatEvent(diContainer),
                this
            )
        }

        super.onEnable()
    }

}