package de.thk.gm.fddw.proxyparcelbox.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WelcomeController {
    @GetMapping("/")
    @ResponseBody
    fun welcome(): String {
        return "Proxy Parcelbox von Mia Henrichsmeyer"
    }
}