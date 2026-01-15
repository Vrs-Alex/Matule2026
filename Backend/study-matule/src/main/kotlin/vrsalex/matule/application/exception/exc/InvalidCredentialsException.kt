package vrsalex.matule.application.exception.exc

import vrsalex.matule.application.exception.MyApplicationException

internal class InvalidCredentialsException(message: String): MyApplicationException(message, 401)