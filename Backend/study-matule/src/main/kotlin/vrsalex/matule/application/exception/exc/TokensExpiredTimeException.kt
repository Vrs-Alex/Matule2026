package vrsalex.matule.application.exception.exc

import vrsalex.matule.application.exception.MyApplicationException

internal class TokensExpiredTimeException(message: String): MyApplicationException(message, 401)