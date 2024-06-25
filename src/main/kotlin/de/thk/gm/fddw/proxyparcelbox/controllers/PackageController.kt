package de.thk.gm.fddw.proxyparcelbox.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import de.thk.gm.fddw.proxyparcelbox.models.Package
import de.thk.gm.fddw.proxyparcelbox.services.PackagesService

@Controller
class PackageController (val packagesService: PackagesService){
    @PostMapping("/newparcelsform")
    @ResponseBody
    fun newParcelsForm(trackingNumber: String, nachbarEmail: String): String {
        var paket: Package = Package()
        paket.trackingNumber = trackingNumber
        paket.nachbarMail = nachbarEmail
        packagesService.save(paket)

        return "Neues Paket"
    }
}