package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.models.Message
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import de.thk.gm.fddw.proxyparcelbox.models.Package
import de.thk.gm.fddw.proxyparcelbox.services.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class PackageController (
    val packagesRestController: PackagesRestController,
    val packagesService: PackagesService,
    val messagesService: MessagesService,
    val messagesServiceImpl: MessagesServiceImpl,
    val usersService: UsersService) {

    data class ChatRequest(
        var trackingNumber: String,
        var nachbarEmail: String,
        var emailUser: String,
        var subscribed: MutableList<String> = mutableListOf()
    )

    companion object {
        private val logger = LoggerFactory.getLogger(PackageController::class.java)
    }

    //Info Pages/Start Page
    @GetMapping("/")
    fun getIndex(model: Model): String {
        return "/index"
    }

    @GetMapping("/ueberuns")
    fun getInfoPage(model: Model): String {
        return "/ueberuns"
    }

    @GetMapping("/support")
    fun getHelpPage(model: Model): String {
        return "/support"
    }

    //QR-Code Page
    @GetMapping("/qrcode/{trackingNumber}")
    fun getQRCode(@PathVariable trackingNumber: String, model: Model, redirectAttributes: RedirectAttributes): String {
        var paket: Package? = packagesService.findByTrackingNumber(trackingNumber)
        model.addAttribute("paket", paket)
        return "/chats/showChat"
    }

    @PostMapping("/parcels")
    fun newParcelsForm(@ModelAttribute chatRequest: ChatRequest, model: Model): String {
        var paket: Package = Package(chatRequest.trackingNumber)
        paket.nachbarMail = chatRequest.nachbarEmail
        paket.emailUser = chatRequest.emailUser
        packagesService.save(paket)

        return "redirect:/qrcode/${paket.id}"
    }

    //Chatroom Page
    @GetMapping("/chats/{trackingNumber}")
    fun getChatByTrackingNumber(@PathVariable("trackingNumber") trackingNumber: String, model: Model): String {
        val chat: Package? = packagesService.findByTrackingNumber(trackingNumber)
        model.addAttribute("chat", chat)

        // Retrieve the messages for this chat room from the database and add them to the model
        val messages = messagesService.getChatRoomMessages(chat!!)
        messages.forEach() {
            logger.info("Message ID: ${it.id}, Text: ${it.text}, Created At: ${it.createdAt}, Sender: ${it.sender}, Email: ${it.email}")
        }
        model.addAttribute("messages", messages)

        return "chats/chatroom"
    }

    @PostMapping("/chats/{trackingNumber}")
    fun saveMessage(@PathVariable("trackingNumber") trackingNumber: String, @RequestBody message: Message): ResponseEntity<Message> {

        logger.info("saveMessage called with trackingNumber: $trackingNumber and message: $message")

        val chat : Package? = packagesService.findByTrackingNumber(trackingNumber)
        return if (chat != null) {
            message.chat = chat
            messagesServiceImpl.createAndSaveMessage(trackingNumber, message.sender, message.text, message.email)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping("/chats/{trackingNumber}/messages")
    @ResponseBody
    fun getChatMessages(@PathVariable("trackingNumber") trackingNumber: String): List<Message> {
        val logger = LoggerFactory.getLogger(PackageController::class.java)

        val chat: Package? = packagesService.findByTrackingNumber(trackingNumber)
        if (chat != null) {
            val messages = messagesService.getChatRoomMessages(chat)
            logger.info("Successfully fetched ${messages.size} messages for chat with tracking number: $trackingNumber")
            return messages
        } else {
            logger.warn("Chat with tracking number $trackingNumber not found")
            return emptyList()
        }
    }

    //Subscription Page
    @GetMapping("/chats/subscribers/{trackingNumber}")
    @ResponseBody
    fun getChatSubscribers(@PathVariable trackingNumber: String): ResponseEntity<List<String>> {
        val subscribers = packagesService.getSubscribers(trackingNumber)
        return ResponseEntity.ok(subscribers)
    }
}