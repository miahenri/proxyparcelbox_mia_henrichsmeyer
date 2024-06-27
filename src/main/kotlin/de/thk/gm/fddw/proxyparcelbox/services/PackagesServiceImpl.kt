package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.Package
import de.thk.gm.fddw.proxyparcelbox.repositories.PackagesRepository
import de.thk.gm.fddw.proxyparcelbox.services.PackagesService
import org.springframework.stereotype.Service

@Service
class PackagesServiceImpl (private val packagesRepository: PackagesRepository) : PackagesService {
    override fun findAll(): List<Package> {
        return packagesRepository.findAll().toList()
    }

    override fun findByTrackingNumber(trackingNumber: String): Package? {
        return packagesRepository.findByTrackingNumber(trackingNumber)
    }

    override fun save(paket: Package) {
        packagesRepository.save(paket)
    }

    override fun delete(paket: Package) {
        packagesRepository.delete(paket)
    }

    override fun update(id: Long, paket: Package): Package {
        return packagesRepository.save(paket)
    }

}