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

    @Transactional
    override fun save(message: Message): Message {
        logger.info("Saving message with ID: ${message.id}, sender: ${message.sender}, text: ${message.text}, associated with package tracking number: ${message.paket?.trackingNumber}")

        val savedMessage = messagesRepository.save(message)

        logger.info("Saved message with ID: ${savedMessage.id}, sender: ${savedMessage.sender}, text: ${savedMessage.text}, associated with package tracking number: ${savedMessage.paket?.trackingNumber} to the database")

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
}