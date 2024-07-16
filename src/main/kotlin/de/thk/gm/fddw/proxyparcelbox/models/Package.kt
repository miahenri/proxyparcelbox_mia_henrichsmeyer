package de.thk.gm.fddw.proxyparcelbox.models

import jakarta.persistence.*

@Entity
@Table(name = "APP_PACKAGE")
class Package(val trackingNumber: String) {
    @Id
    var id: String = trackingNumber
    var nachbarMail: String = ""
    var emailUser: String = ""

    @ElementCollection
    var subscribed: MutableList<String> = mutableListOf()

    override fun toString(): String {
        return "TrackingNumber: $id, Nachbar: $nachbarMail"
    }
}