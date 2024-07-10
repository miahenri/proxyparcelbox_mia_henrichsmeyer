package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.models.Message
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import de.thk.gm.fddw.proxyparcelbox.models.Package
import de.thk.gm.fddw.proxyparcelbox.services.MessagesService
import de.thk.gm.fddw.proxyparcelbox.services.PackagesService
import org.springframework.ui.Model
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class PackageController (
    val packagesRestController: PackagesRestController,
    val packagesService: PackagesService,
    val messagesService: MessagesService) {

    data class ChatRequest(
        var trackingNumber: String,
        var nachbarEmail: String
    )

    @GetMapping("/demo")
    fun getIndex(model: Model): String {
        return "Index"
    }

    @GetMapping("/parcels/{trackingNumber}")
    fun getParcel(@PathVariable trackingNumber: String, model: Model, redirectAttributes: RedirectAttributes): String {
        var paket: Package? = packagesService.findByTrackingNumber(trackingNumber)
        model.addAttribute("paket", paket)
        return "/chats/showChat"
    }

    @PostMapping("/parcels")
    fun newParcelsForm(@ModelAttribute chatRequest: ChatRequest, model: Model): String {
        var paket: Package = Package(chatRequest.trackingNumber)
        paket.nachbarMail = chatRequest.nachbarEmail
        packagesService.save(paket)

        return "redirect:/parcels/${paket.id}"
    }

    @GetMapping("/chats/{trackingNumber}")
    fun getChatByTrackingNumber(@PathVariable("trackingNumber") trackingNumber: String, model: Model): String {
        val chat: Package? = packagesService.findByTrackingNumber(trackingNumber)
        model.addAttribute("chat", chat)

        // Retrieve the messages for this chat room from the database and add them to the model
        val messages = messagesService.getChatRoomMessages(chat!!)
        model.addAttribute("messages", messages)

        return "chats/chatroom"
    }

    @GetMapping("/chats/{trackingNumber}/messages")
    @ResponseBody
    fun getChatMessages(@PathVariable("trackingNumber") trackingNumber: String): List<Message> {
        val chat: Package? = packagesService.findByTrackingNumber(trackingNumber)
        return if (chat != null) {
            messagesService.getChatRoomMessages(chat)
        } else {
            emptyList()
        }
    }
}