package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.User
import de.thk.gm.fddw.proxyparcelbox.repositories.UsersRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UsersServiceImpl (private val usersRepository: UsersRepository) : UsersService {

    companion object {
        private val logger = LoggerFactory.getLogger(MessagesServiceImpl::class.java)
    }

    override fun findById(id: UUID): User? {
        return usersRepository.findByIdOrNull(id)
    }

    override fun findAll(): List<User> {
        return usersRepository.findAll().toList()
    }

    override fun findByEmail(email: String): User? {
        return usersRepository.findByEmail(email)
    }

    override fun getCurrentUserEmail(user: User): String {
        return user.email
    }

    override fun save(user: User) {
        usersRepository.save(user)
        logger.info("User saved: ${user.toString()}")
    }

    override fun delete(user: User) {
        usersRepository.delete(user)
    }
}