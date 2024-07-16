package de.thk.gm.fddw.proxyparcelbox.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name="APP_USER")
class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: UUID = UUID.randomUUID()
    var email: String = ""
    var name: String = ""

    override fun toString(): String {
        return "Id: $id, Name: $name, Email: $email"
    }
}
