package com.pontuaweb.api.exception

import org.springframework.http.HttpStatus

data class ExceptionResponse(
    val status: Int = HttpStatus.INTERNAL_SERVER_ERROR.value(),
    val message: String = "Algo de errado aconteceu, tente novamente !!"
)

data class ExceptionSucccess(
    val status: Int = HttpStatus.CREATED.value(),
    val message: String = "Registro processado com sucesso !!"
)

data class ExceptionNotFound(
    val status: Int = HttpStatus.NOT_FOUND.value(),
    val message: String = "Registro não encontrado !!"
)

data class ExceptionExists(
    val login: String? = null,
    val status: Int = HttpStatus.OK.value(),
    val message: String = "Esse login: [ $login ] está indidponivel !!"
)
