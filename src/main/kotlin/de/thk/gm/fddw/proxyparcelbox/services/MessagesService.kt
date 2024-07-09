package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.Message
import de.thk.gm.fddw.proxyparcelbox.models.Package

interface MessagesService {
    fun save(message: Message): Message
    fun getAllMessages(): List<Message>
    fun getChatRoomMessages(chat: Package): List<Message>
}