package com.pontuaweb.api.controller

import com.pontuaweb.api.entity.User
import com.pontuaweb.api.exception.ExceptionExists
import com.pontuaweb.api.exception.ExceptionNotFound
import com.pontuaweb.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/email/{email}")
    fun getUser(@PathVariable("email") email: String): ResponseEntity<Any> {
        val _email = userService.getByEmail(email)
        return if (_email != null) {
            ResponseEntity(_email, HttpStatus.OK)
        } else {
            ResponseEntity(ExceptionNotFound(), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun postUser(@RequestBody user: User): ResponseEntity<Any> {
        val _user = userService.getByEmail(user.email)
        if (_user == null) {
            userService.postUser(user)
            ResponseEntity(ExceptionExists(), HttpStatus.OK)
        }
        return ResponseEntity(ExceptionExists(login = user.email), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun patchUser(@PathVariable id: Long, @RequestBody user: User) {
        userService.pathUser(id, user)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        if (userService.existsById(id)) {
            userService.delete(id)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity(ExceptionNotFound(), HttpStatus.NOT_FOUND)
    }
}