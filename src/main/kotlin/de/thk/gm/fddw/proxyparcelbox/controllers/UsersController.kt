package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.dtos.UserDTO
import de.thk.gm.fddw.proxyparcelbox.models.User
import de.thk.gm.fddw.proxyparcelbox.services.UsersService
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.PastOrPresent
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
class UsersController(
    private val usersService: UsersService
) {
    @GetMapping("/login")
    fun getIndex(model: Model): String {
        return "/users/login"
    }

    @PostMapping("/login")
    fun createUser(@Valid userDTO: UserDTO): String {
        var user: User = User()
        user.email = userDTO.email
        user.name = userDTO.name
        usersService.save(user)
        return "redirect:/"
    }
}
