package de.thk.gm.fddw.proxyparcelbox.websocketsHandlers

import de.thk.gm.fddw.proxyparcelbox.models.Message
import de.thk.gm.fddw.proxyparcelbox.services.MessagesService
import org.springframework.stereotype.Component
import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*
import kotlin.collections.ArrayList

@Component
class PackageHandler (private val messagesService: MessagesService) : TextWebSocketHandler() {
    private var sessions: ArrayList<WebSocketSession> = ArrayList()
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val chatMessage = Message()
        chatMessage.text = message.payload
        chatMessage.createdAt = Date()
        chatMessage.sender = session.id // Assuming the session ID is the sender

        // Save the message to the database
        messagesService.save(chatMessage)

        // Broadcast the message to all connected sessions
        session.sendMessage(message)
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        sessions.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.removeIf { it.id == session.id }
    }
}