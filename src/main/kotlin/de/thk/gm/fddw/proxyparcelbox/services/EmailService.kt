package de.thk.gm.fddw.proxyparcelbox.services

interface EmailService {
    fun sendSimpleMessage(email: String, subject: String, message: String)
}