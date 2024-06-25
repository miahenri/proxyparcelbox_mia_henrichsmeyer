package de.thk.gm.fddw.proxyparcelbox.models

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name="APP_PACKAGE")
class Package {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    var id : UUID = UUID.randomUUID()
    var trackingNumber : String = ""
    var nachbarMail : String = ""

    override fun toString(): String {
        return "Id: $id, TrackingNumber: $trackingNumber, Nachbar: $nachbarMail"
    }
}