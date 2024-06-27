package de.thk.gm.fddw.proxyparcelbox.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import de.thk.gm.fddw.proxyparcelbox.models.Package
import de.thk.gm.fddw.proxyparcelbox.services.PackagesService
import org.springframework.ui.Model
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class PackageController (val packagesRestController: PackagesRestController, val packagesService: PackagesService){

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
}