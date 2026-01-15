package vrsalex.matule.application.exception.exc

import vrsalex.matule.application.exception.MyApplicationException

internal class InvalidRefreshTokenException(message: String): MyApplicationException(message, 401)