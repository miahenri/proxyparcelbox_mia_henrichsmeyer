package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.models.Package
import de.thk.gm.fddw.proxyparcelbox.services.PackagesService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/packages")
class PackagesRestController (private val packagesService: PackagesService) {

    /*@GetMapping
    fun getAllPackages(): List<Package> {
        return packagesService.findAll()
    }

    @GetMapping("/{id}")
    fun getPackageById(@PathVariable id: Long): Package {
        return packagesService.findById(id)
    }*/

    @PostMapping("/packages")
    @ResponseStatus(HttpStatus.CREATED)
    fun savePackage(@RequestBody paket: Package): Package {
        packagesService.save(paket)
        return paket
    }

    /*@DeleteMapping("/{id}")
    fun deletePackage(@PathVariable id: Long) {
        packagesService.deleteById(id)
    }

    @PutMapping("/{id}")
    fun updatePackage(@PathVariable id: Long, @RequestBody package: Package): Package {
        return packagesService.update(id, package)
    }*/

}