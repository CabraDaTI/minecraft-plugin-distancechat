package org.cabradati.distancechat

import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.distancechat.events.PlayerChatEvent

class App: JavaPlugin() {

    companion object {
        const val ATIVAR_PLUGIN = "plugin.enabled"
        const val ATIVAR_DEBUG = "plugin.debug"
        const val DISTANCIA = "config.distance"
    }

    override fun onEnable() {

        config.addDefault(ATIVAR_PLUGIN, true)
        config.addDefault(ATIVAR_DEBUG, false)
        config.addDefault(DISTANCIA, 28)
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