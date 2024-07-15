package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.*
import de.thk.gm.fddw.proxyparcelbox.repositories.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MessagesServiceImpl(
    val messagesRepository: MessagesRepository,
    val packagesRepository: PackagesRepository
) : MessagesService {

    companion object {
        private val logger = LoggerFactory.getLogger(MessagesServiceImpl::class.java)
    }

    override fun findByChat(paket: Package): List<Message> {
        return messagesRepository.findByChat(paket)
    }

    @Transactional
    override fun save(message: Message): Message {
        logger.info("Saving message with ID: ${message.id}, sender: ${message.sender}, text: ${message.text}, associated with package tracking number: ${message.chat?.trackingNumber}")

        val savedMessage = messagesRepository.save(message)

        logger.info("Saved message with ID: ${savedMessage.id}, sender: ${savedMessage.sender}, text: ${savedMessage.text}, associated with package tracking number: ${savedMessage.chat?.trackingNumber} to the database from email: ${savedMessage.chat?.emailUser}")

        return savedMessage
    }

    @Transactional(readOnly = true)
    override fun getChatRoomMessages(chat: Package): List<Message> {
        logger.info("Fetching messages for package with tracking number: ${chat.id}")

        val messages = messagesRepository.findByChat(chat)

        logger.info("Retrieved ${messages.size} messages for package with tracking number: ${chat.id}")

        return messages
    }

    @Transactional(readOnly = true)
    override fun getAllMessages(): List<Message> {
        return messagesRepository.findAll().toList()
    }

    fun createAndSaveMessage(trackingNumber: String, sender: String, text: String, email: String) {
        // Fetch the Chat with the given tracking number
        val chat = packagesRepository.findByTrackingNumber(trackingNumber)

        // Create a new Message
        val message = Message()
        message.sender = sender
        message.text = text
        message.chat = chat
        message.email = email

        // Save the Message
        save(message)
    }
}