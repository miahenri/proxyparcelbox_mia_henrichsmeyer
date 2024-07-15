package de.thk.gm.fddw.proxyparcelbox.configs

import de.thk.gm.fddw.proxyparcelbox.services.MessagesService
import de.thk.gm.fddw.proxyparcelbox.websocketsHandlers.PackageHandler
import de.thk.gm.fddw.proxyparcelbox.websocketsHandlers.EchoHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.beans.factory.annotation.Autowired

@Configuration
@EnableWebSocket
class WebSocketConfig @Autowired constructor(private val messagesService: MessagesService) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(PackageHandler(messagesService), "/messages")
            //.addHandler(EchoHandler(messagesService), "/echo") // Pass messagesService to EchoHandler
            .setAllowedOrigins("*")
    }
}