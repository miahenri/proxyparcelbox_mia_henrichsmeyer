package de.thk.gm.fddw.proxyparcelbox.repositories

import de.thk.gm.fddw.proxyparcelbox.models.Package
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PackagesRepository : CrudRepository<Package, Long>{
    fun findByTrackingNumber(trackingNumber: String): Package?
}