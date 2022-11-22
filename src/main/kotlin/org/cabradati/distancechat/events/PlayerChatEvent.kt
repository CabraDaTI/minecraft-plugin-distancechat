package org.cabradati.distancechat.events

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.TextComponent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.cabradati.distancechat.DIContainer
import org.cabradati.distancechat.task.EnviarMensagemTask

class PlayerChatEvent(private val diContainer: DIContainer) : Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onChat(event: AsyncChatEvent) {

        event.isCancelled = true

        diContainer.server.scheduler.runTask(
            diContainer.plugin,
            EnviarMensagemTask(
                diContainer,
                event.player,
                event.message() as TextComponent
            )
        )

    }

}