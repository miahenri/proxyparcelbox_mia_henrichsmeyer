package de.thk.gm.fddw.proxyparcelbox.services

interface EmailService {
    fun sendSimpleMessage(email: String, subject: String, message: String)
    fun sendConfirmationEmail(email: String, trackingNumber: String)
    fun sendNotification(email: String, trackingNumber: String, message: String)
}