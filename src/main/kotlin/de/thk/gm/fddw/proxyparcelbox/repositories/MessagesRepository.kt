package de.thk.gm.fddw.proxyparcelbox.repositories

import de.thk.gm.fddw.proxyparcelbox.models.Message
import de.thk.gm.fddw.proxyparcelbox.models.Package
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessagesRepository: CrudRepository<Message, UUID> {
    fun findBySender(sender: String): List<Message>
    fun findByPackageTrackingNumberOrderByCreatedAt(trackingNumber: String): List<Message>
    fun findByPackage(paket: Package): List<Message>
}