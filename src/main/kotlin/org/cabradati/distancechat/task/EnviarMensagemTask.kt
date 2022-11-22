package org.cabradati.distancechat.task

import net.kyori.adventure.text.TextComponent
import org.bukkit.entity.Player
import org.cabradati.distancechat.App
import org.cabradati.distancechat.DIContainer

class EnviarMensagemTask(
    private val diContainer: DIContainer,
    private val player: Player,
    private val mensagem: TextComponent
) : Runnable {

    override fun run() {

        val mensagem = player.name + " > " + mensagem.content()

        val distancia: Double = when {
            mensagem.endsWith("!") -> diContainer.config.getInt(App.DISTANCIA_MAXIMA).toDouble()
            mensagem.endsWith("...") -> diContainer.config.getInt(App.DISTANCIA_MINIMA).toDouble()
            else -> diContainer.config.getInt(App.DISTANCIA).toDouble()
        }

        player.location.getNearbyPlayers(
            distancia, distancia, distancia
        ).forEach { entity ->
            entity.sendMessage(mensagem)
        }

    }

}