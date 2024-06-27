package de.thk.gm.fddw.proxyparcelbox.services
import de.thk.gm.fddw.proxyparcelbox.models.Package


interface PackagesService {
    fun findAll(): List<Package>
    fun findByTrackingNumber(trackingNumber: String): Package?
    fun save(paket: Package)
    fun delete(paket: Package)
    fun update(id: Long, paket: Package): Package
}