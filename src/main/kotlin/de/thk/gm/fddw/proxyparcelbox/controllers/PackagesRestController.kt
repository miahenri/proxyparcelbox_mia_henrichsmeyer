package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.models.Package
import de.thk.gm.fddw.proxyparcelbox.services.PackagesService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/packages")
class PackagesRestController (private val packagesService: PackagesService) {

    @GetMapping
    fun getAllPackages(): List<Package> {
        return packagesService.findAll()
    }

    @GetMapping("/{id}")
    fun getPackageById(@PathVariable id: String): Package? {
        return packagesService.findByTrackingNumber(id)
    }

    @PostMapping("/packages")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePackage(@RequestBody paket: Package): Package {
        packagesService.save(paket)
        return paket
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePackage(@PathVariable id: String) {
        packagesService.delete(packagesService.findByTrackingNumber(id)!!)
    }

    @PutMapping("/{id}")
    fun updatePackage(@PathVariable id: Long, @RequestBody paket: Package): Package {
        return packagesService.update(id, paket)
    }

}