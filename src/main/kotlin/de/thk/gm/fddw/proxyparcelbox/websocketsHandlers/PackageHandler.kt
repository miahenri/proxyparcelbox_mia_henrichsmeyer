package de.thk.gm.fddw.proxyparcelbox.websocketsHandlers

import de.thk.gm.fddw.proxyparcelbox.models.Message
import de.thk.gm.fddw.proxyparcelbox.services.MessagesService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*
import kotlin.collections.ArrayList

@Component
class PackageHandler (private val messagesService: MessagesService) : TextWebSocketHandler() {
    private var sessions: ArrayList<WebSocketSession> = ArrayList()

    companion object {
        private val logger = LoggerFactory.getLogger(PackageHandler::class.java)
    }
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        for (chatSession in sessions) {
            if (chatSession.id != session.id) {
                chatSession.sendMessage(message)
            }
        }
        val messages = messagesService.getAllMessages()
        messages.forEach { msg ->
            logger.info("Message ID: ${msg.id}, Text: ${msg.text}, Created At: ${msg.createdAt}, Sender: ${msg.sender}")
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        sessions.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.removeIf { it.id == session.id }
    }
}