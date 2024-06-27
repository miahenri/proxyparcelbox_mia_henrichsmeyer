package de.thk.gm.fddw.proxyparcelbox.services

import org.springframework.beans.factory.annotation.*
import org.springframework.mail.*
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl (private var emailSender: JavaMailSender) : EmailService {

    @Value("\${spring.mail.username}")
    private lateinit var sender : String
    override fun sendSimpleMessage(email: String, subject: String, message: String) {
        val mail : SimpleMailMessage = SimpleMailMessage()
        mail.setTo(email)
        mail.subject = subject
        mail.from = sender
        mail.text = message
        emailSender.send(mail)
    }
}