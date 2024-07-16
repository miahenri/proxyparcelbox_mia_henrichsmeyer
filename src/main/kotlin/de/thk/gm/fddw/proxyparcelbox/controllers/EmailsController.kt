package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.models.Package
import de.thk.gm.fddw.proxyparcelbox.services.EmailService
import de.thk.gm.fddw.proxyparcelbox.services.PackagesService
import de.thk.gm.fddw.proxyparcelbox.services.ParcelService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class EmailsController (val emailService: EmailService, val parcelService: ParcelService, val packagesService: PackagesService){

    companion object {
        private val logger = org.slf4j.LoggerFactory.getLogger(EmailsController::class.java)
    }
    @GetMapping("/demo/sendemailform")
    fun sendEmailForm() : String {
        return "demo/sendemailform"
    }
    @PostMapping("/demo/emails")
    fun sendEmail(receiver: String, subject: String, message: String) : String {
        emailService.sendSimpleMessage(receiver, subject, message)
        return "redirect:/demo/sendemailform"
    }

    @GetMapping("/demo/testparcelservice/{trackingNumber}")
    @ResponseBody
    fun testParcelService(@PathVariable trackingNumber : String) : String {
        return parcelService.getEmailByTrackingNumber(trackingNumber) }


    @GetMapping("/chats/{trackingNumber}/subscribe")
    fun getEmailSubscription(@PathVariable trackingNumber: String, model: Model) : String {
        val chat: Package? = packagesService.findByTrackingNumber(trackingNumber)
        model.addAttribute("chat", chat)

        return "chats/chatAbo"
    }

    @PostMapping("/chats/{trackingNumber}/subscribe")
    fun subscribe(@PathVariable trackingNumber: String, receiver: String) : String {
        val chat: Package? = packagesService.findByTrackingNumber(trackingNumber)
        if(chat == null) {
            logger.warn("Package $trackingNumber not found")
        } else {
            if (!chat.subscribed.contains(receiver)) {
                chat.subscribed.add(receiver)
                logger.info("Subscribed $receiver to $trackingNumber")
                packagesService.save(chat)
            } else {
                logger.info("User $receiver is already subscribed to $trackingNumber")
            }
        }

        logger.info("Package $trackingNumber has now ${packagesService.findByTrackingNumber(trackingNumber)?.subscribed?.size} subscribers:")
        emailService.sendConfirmationEmail(receiver, trackingNumber)
        return "redirect:/chats/${trackingNumber}"
    }

    @PostMapping("/chats/{trackingNumber}/notify")
    @ResponseBody
    fun notifyByEmail(@PathVariable trackingNumber: String, @RequestBody notificationRequest: NotificationRequest) : String {
        emailService.sendNotification(notificationRequest.email, trackingNumber, notificationRequest.message)
        return "Notification sent"
    }

    data class NotificationRequest(val email: String, val message: String)
}