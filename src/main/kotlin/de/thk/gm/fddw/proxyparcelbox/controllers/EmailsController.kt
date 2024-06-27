package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.services.EmailService
import de.thk.gm.fddw.proxyparcelbox.services.ParcelService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class EmailsController (val emailService: EmailService, val parcelService: ParcelService){
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
}