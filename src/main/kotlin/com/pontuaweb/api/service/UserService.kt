package com.pontuaweb.api.service

import com.pontuaweb.api.entity.User
import com.pontuaweb.api.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class UserService(private val userRepository: UserRepository) {

    fun getByEmail(email: String): User? = userRepository.findByEmail(email)
    fun postUser(user: User): User = userRepository.save(user)

    @Transactional
    open fun pathUser(id: Long, user: User) {
        val userDB = findById(id)
        userDB.email = user.email
        userDB.password = user.password
        userRepository.save(userDB)
    }

    fun findById(id: Long): User = userRepository.findById(id).get()

    fun delete(id: Long) = userRepository.deleteById(id)

    fun existsById(id: Long): Boolean {
        return userRepository.existsById(id)
    }


}