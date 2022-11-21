package org.cabradati.distancechat.events

import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.cabradati.distancechat.App
import org.cabradati.distancechat.DIContainer

class PlayerChatEvent(private val diContainer: DIContainer): Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onChat(event: AsyncChatEvent) {

        event.isCancelled = true

        val mensagem = event.player.name + " > " + event.message()
        val distancia = diContainer.config.getInt(App.DISTANCIA).toDouble()

        val entidadesProximas = event.player.location.getNearbyEntities(
            distancia,
            distancia,
            distancia
        )

        entidadesProximas.filter { entity ->
            entity is Player
        }.forEach { entity ->
            entity.sendMessage(mensagem)
        }

        event.player.sendMessage(mensagem)

    }

}