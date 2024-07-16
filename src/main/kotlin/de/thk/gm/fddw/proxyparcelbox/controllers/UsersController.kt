package de.thk.gm.fddw.proxyparcelbox.controllers

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

    data class UserRequest(
        var name: String,
        var email: String
    )

    @GetMapping("/login")
    fun getIndex(model: Model): String {
        return "/users/login"
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable("id") id: UUID, model: Model): String {
        val user : User? = usersService.findById(id)
        model.addAttribute("user", user)
        return "users/showUser"
    }

    @PostMapping("/login")
    fun createUser(@ModelAttribute userRequest: UsersController.UserRequest, model: Model): String {
        var user: User = User()
        user.email = userRequest.email
        user.name = userRequest.name
        usersService.save(user)
        return "redirect:/"
    }

}